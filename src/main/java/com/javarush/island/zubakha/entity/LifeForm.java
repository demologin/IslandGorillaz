package com.javarush.island.zubakha.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LifeForm {
    private final double weight;
    private final int maxPopulation;
    private final String name;
    @Setter
    private int row;
    @Setter
    private int col;

    public LifeForm(double weight, int maxCountInCell, String name) {
        this.weight = weight;
        this.maxPopulation = maxCountInCell;
        this.name = name;
    }


    }
