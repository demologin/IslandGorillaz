package com.javarush.island.zubakha.entity.map;

import com.javarush.island.zubakha.entity.LifeForm;
import com.javarush.island.zubakha.entity.Plant;
import com.javarush.island.zubakha.entity.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cell {
    private final int row;
    private final int col;
    private final List<Animal> animals;
    private final List<Plant> plants;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animal.setRow(row);
        animal.setCol(col);

        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setCol(col);
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public List<LifeForm> getLifeForms() {
        List<LifeForm> lifeForms = new ArrayList<>();
        lifeForms.addAll(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }
}
