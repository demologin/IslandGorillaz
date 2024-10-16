package com.javarush.island.kozlov.entities.animals.predators;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Snake extends Animal implements AnimalsEat, Movable, Reproduce {


    public Snake() {
        super(15, 30, 1, 3);
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
        long countSnake = location.getAnimals().stream()
                .filter(animal -> animal instanceof Snake)
                .count();

        if (countSnake >= 2) {
            location.addAnimal(new Snake());
            System.out.println("A new snake is born");
        }
    }
}
