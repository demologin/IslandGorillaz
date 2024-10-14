package com.javarush.island.gerasimov.entity.creatures.herbivores;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Rabbit extends Herbivore{

    private String name = "Кролик";
    private String icon = "\uD83D\uDC07";
    private double weight = 2;
    private int maxCountInCell = 150;
    private int maxSpeed = 2;
    private double maxFood = 0.45;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}
