package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Bear extends Animal implements AnimalsEat, Movable, Reproduce {


    public Bear() {
        super(500, 5, 2, 80);
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
        long countBear = location.getAnimals().stream()
                .filter(animal -> animal instanceof Bear)
                .count();

        if (countBear >= 2) {
            location.addAnimal(new Bear());
            System.out.println("A new bear is born");
        }
    }

}
