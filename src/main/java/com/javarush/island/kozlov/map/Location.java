package com.javarush.island.kozlov.map;

import com.javarush.island.kozlov.actions.Reproduce;
import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.plants.Vegetation;
import com.javarush.island.kozlov.exception.ReproductionException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {

    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Vegetation> vegetations = new CopyOnWriteArrayList<>();
    private static final  int MAX_PLANTS = 200;
    private int vegetationsGrown = 0;
    private int vegetationsEaten = 0;

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized List<Animal> getAnimals() {
        return animals;
    }

    public synchronized List<Vegetation> getVegetations() {
        return vegetations;
    }

    public synchronized void growVegetations() {
        if (vegetations.size() < MAX_PLANTS) {
                vegetations.add(new Vegetation(5.0));
                vegetationsGrown++;
                System.out.println("A new vegetation has grown");
        }
    }

    public void eatPlant() {
        if (!vegetations.isEmpty()) {
            vegetations.remove(0);
            vegetationsEaten++;
        }
    }

    public int getVegetationsGrown() {
        return vegetationsGrown;
    }

    public int getVegetationsEaten() {
        return vegetationsEaten;
    }

    public void reproduceAnimals() throws ReproductionException {
        for (Animal animal : animals) {
            if (animal instanceof Reproduce) {
                ((Reproduce) animal).reproduce(this);
            }
        }
    }
}
