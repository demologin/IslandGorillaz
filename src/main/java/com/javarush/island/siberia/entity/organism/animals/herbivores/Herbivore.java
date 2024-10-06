package com.javarush.island.siberia.entity.organism.animals.herbivores;

import com.javarush.island.siberia.entity.organism.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, Location location, double maxFood, int speed) {
        super(weight, location, maxFood, speed);
    }
}