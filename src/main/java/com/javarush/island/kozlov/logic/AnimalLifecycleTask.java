package com.javarush.island.kozlov.logic;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
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
    public Void call() {
        // Логика перемещения
        if (animal instanceof Movable) {
            try {
                ((Movable) animal).move(currentLocation, island);
            } catch (MoveException e) {
                System.out.println("Move failed for " + animal.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        // Логика поедания
        if (animal instanceof AnimalsEat) {
            ((AnimalsEat) animal).eat(currentLocation);
        }

        // Логика размножения
        if (animal instanceof Reproduce) {
            try {
                ((Reproduce) animal).reproduce(currentLocation);
            } catch (ReproductionException e) {
                System.out.println("Reproduction failed for " + animal.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        return null;
    }
}
