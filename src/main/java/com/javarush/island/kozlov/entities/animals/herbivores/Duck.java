package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Duck extends Animal implements AnimalsEat, Movable, Reproduce {

    public Duck() {
        super(1, 200, 4, 0.15);
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
        long countDuck = location.getAnimals().stream()
                .filter(animal -> animal instanceof Duck)
                .count();

        if (countDuck >= 2) {
            location.addAnimal(new Duck());
            System.out.println("A new duck is born");
        }
    }
}
