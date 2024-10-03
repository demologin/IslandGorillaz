package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.GameMap;

public class GameMapCreator {
    private final Initialization entityFactory;

    public GameMapCreator(Initialization entityFactory) {
        this.entityFactory = entityFactory;
    }

    public GameMap createRandomFilledGameMap(int rows, int cols) {
        return createRandomFilledGameMap(rows, cols, false);
    }

    public GameMap createRandomFilledGameMap(int rows, int cols, boolean empty) {
        GameMap gameMap = new GameMap(rows, cols);
        Cell[][] cells = gameMap.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col] = entityFactory.createRandomCell(empty);
            }
        }
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col].updateNextCell(gameMap, row, col);
            }
        }
        return gameMap;
    }
}
