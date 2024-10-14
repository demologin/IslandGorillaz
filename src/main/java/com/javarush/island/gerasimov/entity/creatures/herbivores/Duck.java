package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Duck extends Herbivore{

    private String name = "Утка";
    private String icon = "\uD83E\uDD86";
    private double weight = 1;
    private int maxCountInCell = 200;
    private int maxSpeed = 4;
    private double maxFood = 0.15;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}
