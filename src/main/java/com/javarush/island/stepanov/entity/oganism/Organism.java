package com.javarush.island.stepanov.entity.oganism;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Organism implements Reproduceble,Cloneable {
    @Getter
    protected String name;
    @Getter
    protected String icon;

    @Getter
    protected double maxWeight;
    @Getter
    protected int maxCountInCell;
    @Getter
    protected int flockSize;
    @Setter
    @Getter
    protected double weight;

    @Override
    public Organism clone() {
        try {
            return (Organism) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

}
