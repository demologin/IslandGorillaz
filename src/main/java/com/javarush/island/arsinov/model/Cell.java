package com.javarush.island.arsinov.model;

import com.javarush.island.arsinov.model.animals.Animal;
import com.javarush.island.arsinov.model.plants.Plant;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {

    private static final double MAX_PLANT_MASS = Double.MAX_VALUE;

    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private final Island island;
    @Getter
    private double plantMass;
    @Getter
    private final List<Animal> animals;
    private List<Plant> plants;

    public Cell(int x, int y, Island island, double initialPlants) {
        this.x = x;
        this.y = y;
        this.island = island;
        this.plantMass = initialPlants;
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plants.add(plant.clone());
        increasePlantMass(plantMass);
    }

    public Collection<? extends Character> getPlantSymbols() {
        List<Character> symbols = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant.isMature()) {
                symbols.add('!');
            } else {
                symbols.add('*');
            }
        }
        return symbols;
    }

    public void decreasePlantMass(double amount) {
        plantMass = Math.max(0, plantMass - amount);
    }

    @Override
    public String toString() {
        return "Cell(" + x + ", " + y + ")";
    }

    public void growPlants() {
        Random random = new Random();

        if (getPlantMass() < MAX_PLANT_MASS) {
            for (Plant plant : plants) {
                if (!plant.isMature() && random.nextBoolean()) {
                    plant.grow();
                    if (plant.isMature())
                        increasePlantMass(plantMass);
                }
            }
        }
    }

    public void increasePlantMass(double amount) {
        plantMass = Math.min(MAX_PLANT_MASS, plantMass + amount);
    }

    public double getMaxPlantMass() {
        return MAX_PLANT_MASS;
    }

    public void removeDeadAnimals() {
        animals.removeIf(animal -> !animal.isAlive());
    }
}