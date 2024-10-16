package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Mouse extends Animal implements AnimalsEat, Movable, Reproduce {

    public Mouse() {
        super(0.05, 500, 1, 0.01);
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
        long countMouse = location.getAnimals().stream()
                .filter(animal -> animal instanceof Mouse)
                .count();

        if (countMouse >= 2) {
            location.addAnimal(new Mouse());
            System.out.println("A new mouse is born");
        }
    }
}
