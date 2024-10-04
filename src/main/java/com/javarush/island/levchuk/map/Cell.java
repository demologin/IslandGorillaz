package com.javarush.island.levchuk.map;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    public final int row;
    public final int col;

    List<Cell> adjacentCells = new ArrayList();

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    private void identifyAdjacentCells() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (row - 1 >= 0 && col - 1 >= 0
                        && row + i < IslandMap.islandMap.length
                        && col + j < IslandMap.islandMap[0].length) {
                }
            }
        }
    }
}
