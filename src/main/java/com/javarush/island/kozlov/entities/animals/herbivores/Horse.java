package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Horse extends Animal implements AnimalsEat, Movable, Reproduce {

    public Horse() {
        super(400, 20, 4, 60);
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
        long countHorse = location.getAnimals().stream()
                .filter(animal -> animal instanceof Horse)
                .count();

        if (countHorse >= 2) {
            location.addAnimal(new Horse());
            System.out.println("A new horse is born");
        }
    }
}
