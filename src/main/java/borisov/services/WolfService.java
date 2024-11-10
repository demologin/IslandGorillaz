package borisov.services;

import borisov.api.AnimalsFactory;
import borisov.entity.Animals;
import borisov.entity.map.GameMap;
import borisov.entity.predatoranimal.Wolf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WolfService implements Runnable {
    private GameMap map;

    private Set<? extends Animals> animals;
    private final AnimalsFactory animalsFactory;

    public WolfService(GameMap map, AnimalsFactory animalsFactory) {
        this.map = map;
        this.animalsFactory = animalsFactory;
    }

    @Override
    public void run() {
      animals = animalsFactory.getAllAnimalsMap().get(Wolf.class);
        System.out.println(animals);
        for (Animals animal : animals) {
            Wolf animal1 = (Wolf) animal;
            System.out.println(animal);
            animal1.move();
        }

    }
}
