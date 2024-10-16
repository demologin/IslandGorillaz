package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Boar extends Herbivore{

    private String name = "Кабан";
    private String icon = "\uD83D\uDC17";
    private double weight = 400;
    private int maxCountInCell = 50;
    private int maxSpeed = 2;
    private double maxFood = 50;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}