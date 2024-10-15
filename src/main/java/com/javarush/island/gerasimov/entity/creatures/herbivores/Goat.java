package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Goat extends Herbivore{

    private String name = "Коза";
    private String icon = "\uD83D\uDC10";
    private double weight = 60;
    private int maxCountInCell = 140;
    private int maxSpeed = 3;
    private double maxFood = 10;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}
