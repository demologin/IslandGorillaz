package com.javarush.island.siberia.entity.organism.animals.canivores;

import com.javarush.island.siberia.entity.organism.animals.Animal;

public abstract class Carnivore extends Animal {
    public Carnivore(double weight, Location location, double maxFood, int speed) {
        super(weight, location, maxFood, speed);
    }
}