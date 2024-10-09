package com.javarush.island.siberia.entity.organism.animals;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.utils.RandomUtils;
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
        this.currentFood = RandomUtils.randomDouble(0, this.maxFood);
        this.satiety = this.currentFood / this.maxFood;
    }

    public abstract void eat();

    public abstract void move();

}