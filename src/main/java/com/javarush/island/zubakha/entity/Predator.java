package com.javarush.island.zubakha.entity;

public abstract class Predator extends Animal {

    public Predator(double weight, int speed, double maxFood, int maxCountInCell, String name) {
        super(weight, speed, maxFood, maxCountInCell, name);
    }
}
