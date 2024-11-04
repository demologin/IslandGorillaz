package borisov.services;

import borisov.entity.Animals;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;

import java.util.List;

public class WolfService implements Runnable{
    private  GameMap map;

    private List<? extends Animals> animals;

    public WolfService(GameMap map, List<? extends Animals> animals) {
        this.map = map;
        this.animals = animals;
    }

    @Override
    public void run() {
        //System.out.println(map.toString());

        for (Animals animal : animals) {
            Wolf animal1 = (Wolf) animal;
            animal1.move();
        }

    }
}
