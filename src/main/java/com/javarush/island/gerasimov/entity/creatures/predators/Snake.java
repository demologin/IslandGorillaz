package com.javarush.island.gerasimov.entity.creatures.predators;

import com.javarush.island.gerasimov.entity.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Snake extends Predator{
    private String name = "Удав";
    private String icon = "\uD83D\uDC0D";
    private double weight = 15;
    private int maxCountInCell = 30;
    private int maxSpeed = 1;
    private double maxFood = 3;
    private int probabilityEatenHorse = 0;
    private int probabilityEatenBuffalo = 0;
    private int probabilityEatenBoar = 0;
    private int probabilityEatenDuck = 10;
    private int probabilityEatenSheep = 0;
    private int probabilityEatenGoat = 0;
    private int probabilityEatenRabbit = 20;
    private int probabilityEatenDeer = 0;
    private int probabilityEatenBear = 0;
    private int probabilityEatenFox = 15;
    private int probabilityEatenSnake = 0;
    private int probabilityEatenEagle = 0;
    private int probabilityEatenMouse = 40;
    private int probabilityEatenCaterpillar = 0;
    private int probabilityEatenWolf = 0;
    private Cell targetCell;

    @Override
    public String toString() {
        return getIcon() + getId();
    }
}