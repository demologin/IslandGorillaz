package com.javarush.island.kozlov.entities.plants;

public class Vegetation {

    private static final double MAX_GROWTH_RATE = 0.2;
    private double size;
    private final double nutritionalValue;

    public double getNutritionalValue() {
        return nutritionalValue;
    }

    public Vegetation(double nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public double getSize() {
        return size;
    }

    public void grow() {
        this.size += this.size * MAX_GROWTH_RATE;
        System.out.println("Plants grows. New size: " + this.size);
    }
}
