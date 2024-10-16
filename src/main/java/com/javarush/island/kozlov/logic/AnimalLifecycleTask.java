package com.javarush.island.kozlov.logic;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.concurrent.Callable;

public class AnimalLifecycleTask implements Callable<Void> {
    private final Animal animal;
    private final Location currentLocation;
    private final Island island;

    public AnimalLifecycleTask(Animal animal, Location currentLocation, Island island) {
        this.animal = animal;
        this.currentLocation = currentLocation;
        this.island = island;
    }

    @Override
    public Void call() throws Exception {

        synchronized (currentLocation) {

            if (animal instanceof AnimalsEat) {
                ((AnimalsEat) animal).eat(currentLocation, animal);
            }

            if (animal instanceof Movable) {
                ((Movable) animal).move(currentLocation, island);
            }

            if (animal instanceof Reproduce) {
                ((Reproduce) animal).reproduce(currentLocation);
            }
        }
        return null;
    }
}
