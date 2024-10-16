package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bear extends Predator{

    private String name = "Медведь";
    private String icon = "\uD83D\uDC3B";
    private double weight = 500;
    private int maxCountInCell = 5;
    private int maxSpeed = 2;
    private double maxFood = 80;
    private int probabilityEatenHorse = 40;
    private int probabilityEatenBuffalo = 20;
    private int probabilityEatenBoar = 50;
    private int probabilityEatenDuck = 10;
    private int probabilityEatenSheep = 70;
    private int probabilityEatenGoat = 70;
    private int probabilityEatenRabbit = 80;
    private int probabilityEatenDeer = 80;
    private int probabilityEatenBear = 0;
    private int probabilityEatenFox = 0;
    private int probabilityEatenSnake = 80;
    private int probabilityEatenEagle = 0;
    private int probabilityEatenMouse = 90;
    private int probabilityEatenCaterpillar = 0;
    private int probabilityEatenWolf = 0;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon();
    }
}
