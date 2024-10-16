package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Fox extends Animal implements AnimalsEat, Movable, Reproduce {


    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public void eat(Location location, Animal predator) {
        super.eat(location, predator);
    }

    @Override
    public void reproduce(Location location) {
        long countFox = location.getAnimals().stream()
                .filter(animal -> animal instanceof Fox)
                .count();

        if (countFox >= 2) {
            location.addAnimal(new Fox());
            System.out.println("A new fox is born");
        }
    }

    @Override
    public void move(Location currentLocation, Island island) {
        super.move(currentLocation, island);
    }
}
