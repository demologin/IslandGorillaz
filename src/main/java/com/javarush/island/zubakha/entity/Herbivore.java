package com.javarush.island.zubakha.entity;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int speed, double maxFood, int maxCountInCell, String name) {
        super(weight, speed, maxFood, maxCountInCell, name);
    }

    @Override
    public double getChanceToEat(String foodName) {
        return (foodName.equals("Plant") ? 1 : 0);
    }


}
