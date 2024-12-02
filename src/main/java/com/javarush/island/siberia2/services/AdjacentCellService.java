package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import java.util.ArrayList;
import java.util.List;

public class AdjacentCellService {

    public List<Cell> getAdjacentCells(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();
        Island island = cell.getIsland();
        int width = island.getWidth();
        int height = island.getHeight();

        if (x > 0) {
            neighbors.add(island.getCell(x - 1, y)); //⬅️
            if (y > 0) {
                neighbors.add(island.getCell(x - 1, y - 1)); //↖️
            }
            if (y < height - 1) {
                neighbors.add(island.getCell(x - 1, y + 1)); //↙️
            }
        }
        if (x < width - 1) {
            neighbors.add(island.getCell(x + 1, y)); //➡️
            if (y > 0) {
                neighbors.add(island.getCell(x + 1, y - 1)); //↗️
            }
            if (y < height - 1) {
                neighbors.add(island.getCell(x + 1, y + 1)); //↘️
            }
        }
        if (y > 0) {
            neighbors.add(island.getCell(x, y - 1)); //⬆️
        }
        if (y < height - 1) {
            neighbors.add(island.getCell(x, y + 1)); //⬇️
        }

        return neighbors;
    }
}