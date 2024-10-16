package com.javarush.island.gerasimov.entity.creatures.plants;

import com.javarush.island.gerasimov.entity.creatures.Plant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Grass extends Plant {

    private String name = "Трава";
    private String icon = "\uD83C\uDF3F";
    private double weight = 1;
    private int maxCountInCell = 200;
    private int maxSpeed = 2;

    @Override
    public String toString() {
        return getIcon();
    }
}

