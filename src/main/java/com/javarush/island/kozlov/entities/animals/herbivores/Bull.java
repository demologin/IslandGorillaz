package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Bull extends Animal implements AnimalsEat, Movable, Reproduce {

    public Bull() {
        super(700, 10, 3, 100);
    }

    @Override
    public void move(Location currentLocation, Island island) {
        super.move(currentLocation, island);
    }

    @Override
    public void eat(Location location, Animal predator) {
        super.eat(location, predator);
    }

    @Override
    public void reproduce(Location location) {
        long countBull = location.getAnimals().stream()
                .filter(animal -> animal instanceof Bull)
                .count();

        if (countBull >= 2) {
            location.addAnimal(new Bull());
            System.out.println("A new bull is born");
        }
    }
}
