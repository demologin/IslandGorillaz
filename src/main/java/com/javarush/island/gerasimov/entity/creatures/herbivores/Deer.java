package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Deer extends Herbivore{

    private String name = "Олень";
    private String icon = "\uD83E\uDD8C";
    private double weight = 300;
    private int maxCountInCell = 20;
    private int maxSpeed = 4;
    private double maxFood = 50;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}
