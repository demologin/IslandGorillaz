package com.javarush.island.stepanov.entity.oganism;

import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;

public abstract class Organism implements Eatable, Reproduceble {
    protected String name;
    protected String icon;
    protected double maxWeight;
    protected int maxCountInCell;
    protected int flockSize;
    protected int maxSpeed;
    protected double maxFood;
}
