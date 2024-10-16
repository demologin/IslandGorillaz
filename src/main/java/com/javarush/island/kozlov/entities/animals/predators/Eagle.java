package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Eagle extends Animal implements AnimalsEat, Movable, Reproduce {


    public Eagle() {
        super(6, 20, 3, 1);
    }

    @Override
    public void move(Location currentLocation, Island island) {
        super.move(currentLocation, island);
    }

    @Override
    public void reproduce(Location location) {
        long countEagle = location.getAnimals().stream()
                .filter(animal -> animal instanceof Eagle)
                .count();

        if (countEagle >= 2) {
            location.addAnimal(new Eagle());
            System.out.println("A new eagle is born");
        }
    }

    @Override
    public void eat(Location location, Animal predator) {
        super.eat(location, predator);
    }
}
