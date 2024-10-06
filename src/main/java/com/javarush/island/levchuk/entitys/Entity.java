package com.javarush.island.levchuk.entitys;

import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;


import java.util.concurrent.ThreadLocalRandom;

public class Entity implements Movable {

    private static int maxCountInCell;

    @Override
    public boolean move(Cell currentCell) {
        int nextCell = ThreadLocalRandom.current().nextInt(currentCell.neighbors.size());
        if (checkLimit(currentCell.neighbors.get(nextCell))) {
            takeStep(currentCell.neighbors.get(nextCell),this);
            return true;
        } else {
            for (int i = 0; i < currentCell.neighbors.size() - 1; i++) {
                nextCell++;
                if (nextCell == currentCell.neighbors.size()) {
                    nextCell = 0;
                }
                if (checkLimit(currentCell.neighbors.get(nextCell))) {
                    takeStep(currentCell.neighbors.get(nextCell),this);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLimit(Cell cell) {
        if ((cell.residents.get(this).size() + 1) <= this.maxCountInCell) {
            return true;
        }
        return false;
    }

    public void takeStep(Cell targetCell, Entity entity) {}
}
