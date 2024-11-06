package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.entity.Animals;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;

import java.util.List;

public class WolfService implements Runnable {
    private GameMap map;

    private List<? extends Animals> animals;
    private final AnimalsFactory animalsFactory;

    public WolfService(GameMap map, AnimalsFactory animalsFactory) {
        this.map = map;
        this.animalsFactory = animalsFactory;
    }

    @Override
    public void run() {
      animals = animalsFactory.getWolfs();

        for (Animals animal : animals) {
            Wolf animal1 = (Wolf) animal;

            animal1.move();
        }

    }
}
