package com.javarush.island.stepanov.entity.oganism;

import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;
import lombok.Getter;
import lombok.Setter;

public abstract class Organism implements Eatable, Reproduceble,Cloneable {
    @Getter
    protected String name;
    @Getter
    protected String icon;
    @Getter
    @Setter
    protected double Weight;
    @Getter
    protected double maxWeight;
    @Getter
    protected int maxCountInCell;
    @Getter
    protected int flockSize;

    @Override
    public Organism clone() {
        try {
            return (Organism) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }


}
