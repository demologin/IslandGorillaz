package com.javarush.island.stepanov.repository;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;

import java.util.List;

import static com.javarush.island.stepanov.constants.Constants.FIRST_NUMBRER;

public class GameMapCreator {
    private EntityCreator entityCreator;

    public GameMapCreator(EntityCreator entityCreator) {
        this.entityCreator = entityCreator;
    }

    private static void addNeighbourCells(Cell[][] cells, int i, int j, int xLeft, int xRight, int yTop, int yBottom) {
        List<Cell> nextCell = cells[i][j].getNextCell();
        nextCell.add(cells[xLeft][j]);
        nextCell.add(cells[xRight][j]);
        nextCell.add(cells[i][yTop]);
        nextCell.add(cells[i][yBottom]);
    }

    public GameMap createRandomFilledGameMap(int rows, int cols, boolean b) {
        GameMap gameMap = new GameMap(rows, cols);
        Cell[][] cells = gameMap.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = entityCreator.createRandomCell();
            }
        }
        setCellLists(cells);
        return gameMap;
    }

    private void setCellLists(Cell[][] cells) {
        int maxX = Setting.get().getRows() - 1; // Максимальный индекс строки
        int maxY = Setting.get().getCols() - 1; // Максимальный индекс столбца
        int xLeft;
        int xRight;
        int yTop;
        int yBottom;

        for (int i = 0; i < cells.length; i++) {
            if (i != FIRST_NUMBRER) {
                xLeft = i - 1;
            } else {
                xLeft = maxX;
            }
            if (i != maxX) {
                xRight = i + 1;
            } else {
                xRight = FIRST_NUMBRER;
            }
            for (int j = 0; j < cells[i].length; j++) {
                if (j != FIRST_NUMBRER) {
                    yTop = j - 1;
                } else {
                    yTop = maxY;
                }
                if (j != maxY) {
                    yBottom = j + 1;
                } else {
                    yBottom = FIRST_NUMBRER;
                }
                addNeighbourCells(cells, i, j, xLeft, xRight, yTop, yBottom);
            }
        }
    }

}
