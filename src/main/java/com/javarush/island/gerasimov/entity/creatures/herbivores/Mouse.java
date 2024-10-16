package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Mouse extends Herbivore    {

    private String name = "Мышь";
    private String icon = "\uD83D\uDC2D";
    private double weight = 0.05;
    private int maxCountInCell = 500;
    private int maxSpeed = 1;
    private double maxFood = 0.01;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}
