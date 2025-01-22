package com.javarush.island.stepanov.entity.map;

import lombok.Getter;

import java.util.HashMap;

public class GameMap {
    @Getter
    private final Cell[][] cells;
    @Getter
    private final GeneralStatisticsMap generalStatisticsMap;

    public GameMap(int rows, int colls) {
        cells = new Cell[rows][colls];
        generalStatisticsMap= new  GeneralStatisticsMap();
    }
}
