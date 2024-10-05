package com.javarush.island.levchuk.entitys;

import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;


import java.util.concurrent.ThreadLocalRandom;

public class Entity implements Movable {

    private static int maxCountInCell;

    @Override
    public void move(IslandMap islandMap, Cell currentCell) {
        int nextCell = ThreadLocalRandom.current().nextInt(currentCell.neighbors.size());
        if (checkLimit(currentCell.neighbors.get(nextCell))){

        }
        Cell nextCellCell = currentCell.neighbors.get(nextCell);
    }

    public boolean checkLimit(Cell cell) {
        if ((cell.residents.get(this).size() + 1) <= this.maxCountInCell) {
            return true;
        }
        return false;
    }
}
