package com.javarush.island.kozlov.entities.animals.herbivores;


import com.javarush.island.kozlov.actions.Herbivore;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.plants.Vegetation;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.List;


public class Deer extends Animal implements Herbivore{

    public Deer() {
        super(300, 20, 4, 50);
    }

    @Override
    public void eat(Location location) {
        // Логика поедания растений
        List<Vegetation> plants = location.getPlants();
        if (!plants.isEmpty()) {
            Vegetation plant = plants.remove(0);  // Съедаем растение
            this.setFoodNeed(Math.max(this.getFoodNeed() - plant.getNutritionalValue(), 0));
            System.out.println(this.getClass().getSimpleName() + " eats vegetation.");
            this.resetHungerCycles();
        } else {
            System.out.println(this.getClass().getSimpleName() + " couldn't find vegetation.");
            this.increaseHungerCycles();
        }
    }

    @Override
    public void move(Location currentLocation, Island island) throws MoveException {
        List<Location> neighbors = island.getNeighboringLocations(currentLocation);
        for (Location neighbor : neighbors) {
            if (neighbor.getAnimals().size() < neighbor.getMaxOnCell()) {
                synchronized (neighbor) {
                    currentLocation.removeAnimal(this);
                    neighbor.addAnimal(this);
                }
                System.out.println(this.getClass().getSimpleName() + " moved to a new location.");
                return;
            }
        }
        throw new MoveException(this.getClass().getSimpleName() + " could not move.");
    }

    @Override
    public void reproduce(Location location) throws ReproductionException {
        long countSameSpecies = location.getAnimals().stream()
                .filter(a -> a.getClass().equals(this.getClass()))
                .count();

        if (countSameSpecies >= 2) {
            location.addAnimal(new Rabbit());  // Рождение нового кролика
            this.markAsBorn();
            System.out.println("A new " + this.getClass().getSimpleName() + " is born.");
        } else {
            throw new ReproductionException(this.getClass().getSimpleName() + " couldn't reproduce.");
        }
    }
}
