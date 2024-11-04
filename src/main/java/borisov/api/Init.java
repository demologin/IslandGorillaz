package borisov.api;

import borisov.config.AnimalsList;
import borisov.config.MyConfig;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;

import borisov.services.GameService;

import java.util.Map;


public class Init {

    Cell cell;
    public GameMap map ;
    private final AnimalsFactory animalsFactory;


    public Init() {
        this.map = new GameMap(MyConfig.MAP_WIDTH, MyConfig.MAP_HEIGHT);
        this.animalsFactory = new AnimalsFactory(map);
    }

    public void initialize(){
        Map<AnimalsList,Integer> animalsCount = AnimalsList.getAll();
        for (Map.Entry<AnimalsList,Integer> animal : animalsCount.entrySet()){
            animalsFactory.startProduce(animal.getKey(),animal.getValue());
        }

        System.out.println(map.toString());
        GameService gameService = new GameService(map,animalsFactory);
        gameService.start();

//        AnimalsService animalsService = new AnimalsService(map,animalsFactory.getWolfs());
//        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(4)) {
//            executor.scheduleAtFixedRate(animalsService, 0, 1, TimeUnit.SECONDS);
//
//            executor.awaitTermination(1, TimeUnit.DAYS);
//
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(map.toString() + "\nafter delay");
//        try {
//            //Thread.sleep(2000);
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }



    }

}
