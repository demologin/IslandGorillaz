package com.javarush.island.arsinov.model.animals;

public class AnimalAttributes {

    private double weight;
    private int maxPerCell;
    private int maxMoveDistance;
    private final double foodRequire;


    public AnimalAttributes(double weight, int maxPerCell,int maxMoveDistance, double foodRequire) {
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.maxMoveDistance = maxMoveDistance;
        this.foodRequire = foodRequire;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public int getMaxMoveDistance() {
        return maxMoveDistance;
    }

    public double getFoodRequire() {
        return foodRequire;
    }
}