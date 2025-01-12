package com.javarush.island.stepanov.repository;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;

public class GameMapCreator {
    private EntityCreator entityCreator;

    public GameMapCreator(EntityCreator entityCreator) {
        this.entityCreator = entityCreator;
    }

    public GameMap createRandomFilledGameMap(int rows, int cols, boolean b) {
        GameMap gameMap = new GameMap(rows, cols);
        Cell[][] cells = gameMap.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = entityCreator.createRandomCell();
            }
        }
        return gameMap;
    }
}
