package com.javarush.island.kozlov.entities.animals;

import com.javarush.island.kozlov.map.Location;

public abstract class Animal {

    public double weight;
    protected int maxOnCell;
    protected int speed;
    public double foodNeed;

    public Animal (double weight, int maxOnCell, int speed, double foodNeed) {

        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
    }

    // Метод для передвижения
    public abstract void move();

    // Метод для размножения
    public abstract void reproduce();

    // Метод для питания
    public abstract void eat(Location location);
}
