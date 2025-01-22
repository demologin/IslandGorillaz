package com.javarush.island.stepanov.entity.map;

import lombok.Getter;

import java.util.HashMap;

public class GameMap {
    @Getter
    private final Cell[][] cells;
    @Getter
    private HashMap<String, Integer> generalStatistics;

    public GameMap(int rows, int colls) {
        generalStatistics = new HashMap<>();
        cells = new Cell[rows][colls];

    }
}
