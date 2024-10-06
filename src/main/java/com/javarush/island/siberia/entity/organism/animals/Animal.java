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

    public Animal(Location location) {
        super(location);
        this.maxFood = this.getOrganismSettings().getMaxFood();
        this.speed = this.getOrganismSettings().getSpeed();
        this.currentFood = 0;
        this.satiety = 0;
    }

    public abstract void eat();
    public abstract void move();

}