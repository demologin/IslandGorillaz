package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.plants.Plant;
import lombok.Getter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Cell {

    @Getter
    private final Island island;
    @Getter
    private final int x;
    @Getter
    private final int y;
    private final boolean isWater;
    private final Queue<Animal> animals = new ConcurrentLinkedQueue <>();
    private final Queue<Plant> plants = new ConcurrentLinkedQueue<>();

    public Cell(int x, int y, boolean isWater, Island island) {
        this.x = x;
        this.y = y;
        this.isWater = isWater;
        this.island = island;
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Plant> getPlants() {
        return List.copyOf(plants);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public boolean isWater() {
        return isWater;
    }

}