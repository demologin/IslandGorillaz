package com.javarush.island.siberia2.entity;

import com.javarush.island.siberia2.entity.map.Cell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Organism implements Cloneable{
    protected double weight;
    protected Cell currentCell;

    public abstract void liveCycle();

    @Override
    public Organism clone() throws CloneNotSupportedException{
        return (Organism) super.clone();
    }

}