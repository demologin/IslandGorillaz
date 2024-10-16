package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class Horse extends Herbivore {

    private String name = "Лошадь";
    private String icon = "\uD83D\uDC0E";
    private double weight = 300;
    private int maxCountInCell = 20;
    private int maxSpeed = 4;
    private double maxFood = 60;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}
