package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;


public class PlantService extends Organism implements Eatable, Reproduceble,Cloneable {

    @Override
    public void eat() {
        System.out.printf("%s is weight %.2f%n", name, getWeight());
    }

    @Override
    public void reproduce() {
//        System.out.println(name + " is reproducing");
    }

    @Override
    public PlantService clone() {
        return (PlantService) super.clone();
    }
}
