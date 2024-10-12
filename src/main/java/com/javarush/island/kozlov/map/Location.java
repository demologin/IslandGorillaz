package com.javarush.island.kozlov.map;

import com.javarush.island.kozlov.entities.animals.Animal;
import com.javarush.island.kozlov.entities.plants.Vegetation;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private List<Animal> animals = new ArrayList<>();
    private List<Vegetation> vegetations = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Vegetation> getVegetations() {
        return vegetations;
    }
}
