package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;

public class AnimalService extends Organism implements Movable, Reproduceble, Eatable,Cloneable {
    protected int maxSpeed;
    protected double maxFood;

    @Override
    public void eat() {
        System.out.println(name + " is eating" + maxFood);
    }

    @Override
    public void move() {
        System.out.println(name + " is moving");
    }

    @Override
    public void reproduce() {
        System.out.println(name + " is reproducing");
    }

    @Override
    public AnimalService clone() {
        return (AnimalService) super.clone();
    }
}
