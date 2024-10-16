package com.javarush.island.kozlov.entities.animals.herbivores;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.animals.predators.Wolf;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

public class Rabbit extends Animal implements AnimalsEat, Movable, Reproduce {

    public Rabbit() {
        super(2, 150, 2, 0.45);

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
        long countRabbit = location.getAnimals().stream()
                .filter(animal -> animal instanceof Rabbit)
                .count();

        if (countRabbit >= 2) {
            location.addAnimal(new Rabbit());
            System.out.println("A new rabbit is born");
        }
    }
}
