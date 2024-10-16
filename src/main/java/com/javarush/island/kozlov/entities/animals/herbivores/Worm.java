package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Worm extends Animal implements AnimalsEat, Movable, Reproduce {

    public Worm() {
        super(0.01, 1000, 0, 0);
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
        long countWorm = location.getAnimals().stream()
                .filter(animal -> animal instanceof Worm)
                .count();

        if (countWorm >= 2) {
            location.addAnimal(new Worm());
            System.out.println("A new worm is born");
        }
    }
}
