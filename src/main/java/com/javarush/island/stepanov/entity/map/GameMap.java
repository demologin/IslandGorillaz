package com.javarush.island.stepanov.entity.map;

import lombok.Getter;

public class GameMap {
    @Getter
    private final Cell[][] cells;
    @Getter
    private final GeneralStatistic generalStatisticsMap;

    public GameMap(int rows, int colls) {
        cells = new Cell[rows][colls];
        generalStatisticsMap = new GeneralStatistic();
    }
}
