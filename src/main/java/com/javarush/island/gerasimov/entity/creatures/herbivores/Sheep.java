package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Sheep extends Herbivore{

    private String name = "Овца";
    private String icon = "\uD83D\uDC11";
    private double weight = 70;
    private int maxCountInCell = 140;
    private int maxSpeed = 3;
    private double maxFood = 15;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}
