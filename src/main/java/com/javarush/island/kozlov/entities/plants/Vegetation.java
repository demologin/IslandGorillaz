package com.javarush.island.kozlov.entities.plants;

public class Vegetation {
    private double size;
    private static final double MAX_SIZE = 10.0;

    public Vegetation(double initialSize) {
        this.size = initialSize;
    }

    // Метод для роста растений
    public void grow() {
        if (size < MAX_SIZE) {
            size += size * 0.2;
            System.out.println("A new vegetation has grown. Size: " + size);
        }
    }

    public double getSize() {
        return size;
    }

    public double getNutritionalValue() {
        return size / 2;
    }
}
