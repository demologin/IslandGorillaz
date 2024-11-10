package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.config.MyConfig;
import borisov.entity.map.GameMap;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameService extends Thread{
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

        WolfService wolfService = new WolfService(map,animalFactory);
        RabbitService rabbitService = new RabbitService(map,animalFactory) ;

        try (ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE)) {
                try {


                mainPool.scheduleAtFixedRate(wolfService, PERIOD, PERIOD, TimeUnit.MILLISECONDS);
                mainPool.scheduleAtFixedRate(rabbitService, PERIOD, PERIOD, TimeUnit.MILLISECONDS);
                }catch (Exception e) {
                    e.printStackTrace();
                }

                while(true){
                    Thread.sleep(PERIOD+100);
                    System.out.println(map.toString());

                }

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
