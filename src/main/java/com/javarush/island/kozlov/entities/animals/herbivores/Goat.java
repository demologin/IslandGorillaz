package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Goat extends Animal implements AnimalsEat, Movable, Reproduce {

    public Goat() {
        super(60, 140, 3, 10);
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
        long countGoat = location.getAnimals().stream()
                .filter(animal -> animal instanceof Goat)
                .count();

        if (countGoat >= 2) {
            location.addAnimal(new Goat());
            System.out.println("A new goat is born");
        }
    }
}
