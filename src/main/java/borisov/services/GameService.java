package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.config.AnimalsList;
import borisov.config.MyConfig;
import borisov.entity.Animals;
import borisov.entity.map.GameMap;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameService extends Thread {
    public static final int CORE_POOL_SIZE = 4;
    private final int PERIOD = MyConfig.PERIOD;
    private final AnimalsFactory animalFactory;


    public GameService(GameMap map, AnimalsFactory animalFactory) {
        this.map = map;
        this.animalFactory = animalFactory;
    }

    GameMap map;


    @Override
    public void run() {

        try (ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE)) {
            for (AnimalsList value : AnimalsList.values()) {

                mainPool.scheduleAtFixedRate((new AnimalService<>(map, animalFactory, value.getAnimalClass())), PERIOD, PERIOD, TimeUnit.MILLISECONDS);

            }

            while (true) {
                try {
                    Thread.sleep(PERIOD);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Map<Class<? extends Animals>, Set<Animals>> allAnimalsMap = animalFactory.getAllAnimalsMap();
                for (Map.Entry<Class<? extends Animals>, Set<Animals>> entry : allAnimalsMap.entrySet()) {
                    System.out.printf("%s - %s | ", entry.getKey().getSimpleName(), entry.getValue().size());
                }
                System.out.println("\n" + map.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
