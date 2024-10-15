package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Eagle extends Predator{

    private String name = "Орел";
    private String icon = "\uD83E\uDD85";
    private double weight = 6;
    private int maxCountInCell = 20;
    private int maxSpeed = 3;
    private double maxFood = 1;
    private int probabilityEatenHorse = 0;
    private int probabilityEatenBuffalo = 0;
    private int probabilityEatenBoar = 0;
    private int probabilityEatenDuck = 80;
    private int probabilityEatenSheep = 0;
    private int probabilityEatenGoat = 0;
    private int probabilityEatenRabbit = 90;
    private int probabilityEatenDeer = 0;
    private int probabilityEatenBear = 0;
    private int probabilityEatenFox = 10;
    private int probabilityEatenSnake = 0;
    private int probabilityEatenEagle = 0;
    private int probabilityEatenMouse = 90;
    private int probabilityEatenCaterpillar = 0;
    private int probabilityEatenWolf = 0;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}
