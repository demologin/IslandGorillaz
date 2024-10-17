package com.javarush.island.kozlov.entities.animals;

import com.javarush.island.kozlov.actions.AnimalsEat;
import com.javarush.island.kozlov.actions.Herbivore;
import com.javarush.island.kozlov.actions.Movable;
import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.plants.Vegetation;
import com.javarush.island.kozlov.exception.MoveException;
import com.javarush.island.kozlov.exception.ReproductionException;
import com.javarush.island.kozlov.logic.ProbabilityTable;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.map.Location;

import java.util.List;

public abstract class Animal implements AnimalsEat, Movable, Reproduce {

    public double weight;
    protected int maxOnCell;
    protected int speed;
    public double foodNeed;

    public Animal(double weight, int maxOnCell, int speed, double foodNeed) {

        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
    }

    // Метод для передвижения
    public void move(Location currentLocation, Island island) throws MoveException {
        Location newLocation = island.getRandomNeighboringLocation(currentLocation);

        if (newLocation != null) {
            synchronized (newLocation) {
                synchronized (currentLocation) {
                    if (newLocation.getAnimals().size() < ProbabilityTable.getMaxOnCell(this.getClass())) {
                        currentLocation.removeAnimal(this);
                        newLocation.addAnimal(this);
                        System.out.println(this.getClass().getSimpleName() + " moves to a new location");
                    } else {
                        throw new MoveException("Cannot move to new location, it's full");
                    }
                }
            }
        } else {
            throw new MoveException("No available neighboring locations to move");
        }
    }

    // Метод для размножения
    public void reproduce(Location location) throws ReproductionException {
        long countAnimals = location.getAnimals().stream()
                .filter(animal -> animal.getClass() == this.getClass())
                .count();

        if (countAnimals >= 2) {
            if (location.getAnimals().size() < ProbabilityTable.getMaxOnCell(this.getClass())) {
                try {
                    location.addAnimal(this.getClass().getConstructor().newInstance());
                    System.out.println("A new " + this.getClass().getSimpleName() + " is born.");
                } catch (Exception e) {
                    throw new ReproductionException("Error while creating a new animal: " + e.getMessage());
                }
            } else {
                throw new ReproductionException("Cannot reproduce, the location is full.");
            }
        } else {
            throw new ReproductionException("Not enough animals to reproduce.");
        }
    }


    // Метод для питания
    @Override
    public void eat(Location location, Animal animal) {
        if (animal instanceof Herbivore) {
            List<Vegetation> plants = location.getVegetations();
            if (!plants.isEmpty()) {
                Vegetation plant = plants.remove(0);
                animal.foodNeed = Math.max(animal.foodNeed - plant.getNutritionalValue(), 0);
                System.out.println(animal.getClass().getSimpleName() + " eats vegetation ");
            } else {
                System.out.println(animal.getClass().getSimpleName() + " has no plants to eat");
            }
        } else {
            List<Animal> animals = location.getAnimals();
            for (Animal prey : animals) {
                if (prey == animal) continue;
                boolean success = ProbabilityTable.tryToEat(animal.getClass(), prey.getClass());
                if (success) {
                    System.out.println(animal.getClass().getSimpleName() + " eats " + prey.getClass().getSimpleName());
                    animal.foodNeed = Math.max(animal.foodNeed - prey.weight, 0);
                    location.removeAnimal(prey);
                    if (animal.foodNeed == 0) {
                        System.out.println(animal.getClass().getSimpleName() + " is full ");
                        break;
                    }
                }
            }
        }
        if (animal.foodNeed > 0) {
            System.out.println(animal.getClass().getSimpleName() + " is still hungry");
        }
    }

    private boolean bornRecently = false;
    private boolean eatenRecently = false;

    public void markAsBorn() {
        bornRecently = true;
    }

    public boolean isBornRecently() {
        return bornRecently;
    }

    public void markAsEaten() {
        eatenRecently = true;
    }

    public boolean isEatenRecently() {
        return eatenRecently;
    }

    public void resetFlags() {
        bornRecently = false;
        eatenRecently = false;
    }
}