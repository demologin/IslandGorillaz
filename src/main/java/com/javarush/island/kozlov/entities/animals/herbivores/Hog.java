package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Hog extends Animal implements AnimalsEat, Movable, Reproduce {

    public Hog() {
        super(400, 50, 2, 50);
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
        long countHog = location.getAnimals().stream()
                .filter(animal -> animal instanceof Hog)
                .count();

        if (countHog >= 2) {
            location.addAnimal(new Hog());
            System.out.println("A new hog is born");
        }
    }
}
