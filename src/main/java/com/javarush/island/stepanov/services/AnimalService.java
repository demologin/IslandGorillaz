package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;

public class AnimalService extends Organism implements Movable, Reproduceble, Eatable {

    @Override
    public void eat() {
        System.out.println(this.getClass().getSimpleName() + " is eating" + maxFood);
    }

    @Override
    public void move() {
        System.out.println(this.getClass().getSimpleName() + " is moving");
    }

    @Override
    public void reproduce() {
        System.out.println(this.getClass().getSimpleName() + " is reproducing");
    }
}
