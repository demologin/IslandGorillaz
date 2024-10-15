package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.plants.Plant;
import lombok.Getter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {

    @Getter
    private final Island island;
    @Getter
    private final int x;
    @Getter
    private final int y;
    private final boolean isWater;
    private final CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();

    public Cell(int x, int y, boolean isWater, Island island) {
        this.x = x;
        this.y = y;
        this.isWater = isWater;
        this.island = island;
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Cell cell = (Cell) o;
//
//        if (x != cell.x) return false;
//        return y == cell.y;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y);
//    }

}