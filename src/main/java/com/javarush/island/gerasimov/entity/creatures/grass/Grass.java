package com.javarush.island.gerasimov.entity.creatures.grass;

import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@EqualsAndHashCode(callSuper = true)
@Data
public class Grass extends Plant {

    private String name = "Трава";
    private String icon = "\uD83C\uDF3F";
    private double weight = 1;
    private int maxCountInCell = 200;
    private int maxSpeed = 1;

    @Override
    public String toString() {
        return getIcon()+getId();
    }
}

