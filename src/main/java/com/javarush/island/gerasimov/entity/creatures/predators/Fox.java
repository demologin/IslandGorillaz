package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Fox extends Predator{

    private String name = "Лиса";
    private String icon = "\uD83E\uDD8A";
    private double weight = 8;
    private int maxCountInCell = 30;
    private int maxSpeed = 2;
    private double maxFood = 2;
    private int probabilityEatenHorse = 0;
    private int probabilityEatenBuffalo = 0;
    private int probabilityEatenBoar = 0;
    private int probabilityEatenDuck = 60;
    private int probabilityEatenSheep = 0;
    private int probabilityEatenGoat = 0;
    private int probabilityEatenRabbit = 70;
    private int probabilityEatenDeer = 0;
    private int probabilityEatenBear = 0;
    private int probabilityEatenFox = 0;
    private int probabilityEatenSnake = 0;
    private int probabilityEatenEagle = 0;
    private int probabilityEatenMouse = 90;
    private int probabilityEatenCaterpillar = 40;
    private int probabilityEatenWolf = 0;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}