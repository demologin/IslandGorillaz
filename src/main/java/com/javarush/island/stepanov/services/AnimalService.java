package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Movable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;

import java.util.Map;

public class AnimalService extends Organism implements Movable, Reproduceble, Eatable,Cloneable {
    protected int maxSpeed;
    protected double maxFood;

    @Override
    public void eat() {
        System.out.printf("%s is weight %.2f%n", name, getWeight());
        System.out.println("can eat :");
        Map<String,Integer> foodMap = Setting.get().getFoodMap(name);
        for (Map.Entry<String,Integer> entry : foodMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Override
    public void move() {
//        System.out.println(name + " is moving");
    }

    @Override
    public void reproduce() {
//        System.out.println(name + " is reproducing");
    }

    @Override
    public AnimalService clone() {
        return (AnimalService) super.clone();
    }
}
