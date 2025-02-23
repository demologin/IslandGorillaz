package borisov.api;

import borisov.config.MyConfig;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;

import borisov.services.GameService;

import java.util.Map;
import java.util.Set;


public class Init {


    public GameMap map ;
    private final AnimalsFactory animalsFactory;



    public Init() {
        this.map = new GameMap(MyConfig.MAP_WIDTH, MyConfig.MAP_HEIGHT);
        this.animalsFactory = new AnimalsFactory(map);
    }

    public void initialize(){
        ConfigReader.getInstance();
        animalsFactory.startProduce();

        Map<Class<? extends Animals>, Set<Animals>> allAnimalsMap = animalsFactory.getAllAnimalsMap();
        for(Map.Entry<Class<? extends Animals>, Set<Animals>> entry : allAnimalsMap.entrySet()){
            Set<Animals> animals = entry.getValue();
            for (Animals animal : animals) {
                int x = MyRandomUtil.random(0,MyConfig.MAP_WIDTH);
                int y = MyRandomUtil.random(0,MyConfig.MAP_HEIGHT);
                Cell cell = map.getCell(x,y);
                cell.setAnimalInCell(animal);
                animal.setPosition(cell);
            }

        }


        GameService gameService = new GameService(map,animalsFactory);
        gameService.start();





    }

}
