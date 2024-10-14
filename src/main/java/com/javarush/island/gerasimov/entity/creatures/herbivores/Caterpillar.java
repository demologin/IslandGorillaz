package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Caterpillar extends Herbivore {

    private String name = "Гусеница";
    private String icon = "\uD83D\uDC1B";
    private double weight = 0.01;
    private int maxCountInCell = 1000;
    private int maxSpeed = 0;
    private double maxFood = 0;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}
