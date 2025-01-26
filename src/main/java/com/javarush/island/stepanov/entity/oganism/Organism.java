package com.javarush.island.stepanov.entity.oganism;

import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;
import com.javarush.island.stepanov.entity.oganism.interfaces.Starving;
import com.javarush.island.stepanov.exception.AppException;
import lombok.Getter;
import lombok.Setter;

import static com.javarush.island.stepanov.constants.Constants.CLONE_EXCEPTION;

public abstract class Organism implements Starving,Reproduceble,Cloneable,Eatable {
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
            throw new AppException(CLONE_EXCEPTION, e);
        }
    }

}
