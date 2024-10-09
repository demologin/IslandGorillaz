package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.entitys.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Cell {

    public final int row;
    public final int col;
    public List<Cell> neighbors;
    public HashMap< Class<? extends Entity>, Set<Entity>> residents;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void findNeighbors(Cell[][] array) {
        List<Cell> cells = new ArrayList();
        for (int deltaRow = -1; deltaRow < 2; deltaRow++) {
            for (int deltaCol = -1; deltaCol < 2; deltaCol++) {
                if (deltaRow == 0 && deltaCol == 0) {
                    continue;
                }
                int newRow = this.row + deltaRow;
                int newCol = this.col + deltaCol;
                if (newRow >= 0 && newRow < array.length && newCol >= 0 && newCol < array[0].length) {
                    cells.add(array[newRow][newCol]);
                }
            }
        }
        this.neighbors = cells;
    }
    public boolean checkLimit(Entity entity){

        return false;
    }
}
