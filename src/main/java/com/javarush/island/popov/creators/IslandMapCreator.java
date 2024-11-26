package com.javarush.island.popov.creators;

import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.map.IslandMap;

public class IslandMapCreator {

    public IslandMap createMap(int rows, int cols) {
        IslandMap islandMap = new IslandMap(rows, cols);
        Cell[][] cells = islandMap.getCells();
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                Cell cell = new Cell(y, x);
                UnitsCreator.fill(cell);
                cells[y][x] = cell;
            }
        }
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                cells[y][x].updateNextCell(islandMap, y, x);
            }
        }
        return islandMap;
    }

}
