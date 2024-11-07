package borisov.api;

import borisov.config.AnimalsList;
import borisov.config.MyConfig;
import borisov.entity.map.GameMap;

import borisov.services.GameService;

import java.util.Map;


public class Init {


    public GameMap map ;
    private final AnimalsFactory animalsFactory;
    public ConfigReader configReader;


    public Init() {
        this.map = new GameMap(MyConfig.MAP_WIDTH, MyConfig.MAP_HEIGHT);
        this.animalsFactory = new AnimalsFactory(map);
    }

    public void initialize(){
        ConfigReader.getInstance();
        Map<AnimalsList,Integer> animalsCount = AnimalsList.getAll();
        for (Map.Entry<AnimalsList,Integer> animal : animalsCount.entrySet()){
            animalsFactory.startProduce(animal.getKey(),animal.getValue());
        }




        GameService gameService = new GameService(map,animalsFactory);
        gameService.start();





    }

}
