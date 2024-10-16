package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Buffalo extends Herbivore{

    private String name = "Буйвол";
    private String icon = "\uD83D\uDC03";
    private double weight = 700;
    private int maxCountInCell = 10;
    private int maxSpeed = 3;
    private double maxFood = 100;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}
