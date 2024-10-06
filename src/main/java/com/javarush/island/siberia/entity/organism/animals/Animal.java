package com.javarush.island.siberia.entity.organism.animals;

import com.javarush.island.siberia.entity.organism.Organism;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal extends Organism {
    private double maxFood;
    private double currentFood;
    private int speed;
    private double satiety;

    public Animal(double weight, Location location, double maxFood, int speed) {
        super(weight, location);
        this.maxFood = maxFood;
        this.currentFood = 0;
        this.speed = speed;
        this.satiety = 0;
    }

    public abstract void eat();
    public abstract void move();

}