package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.entities.Entity;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Cell {

    private final ReentrantLock lock = new ReentrantLock();
    private final int row;
    private final int col;
    private Set<Cell> neighbors;
    private Map<Class<? extends Entity>, List<Entity>> residents = new HashMap<>();

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void findNeighbors(Cell[][] array) {
        Set<Cell> cells = new HashSet<>();
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

    public void addEntity(Entity entity) {
        Class<? extends Entity> clazz = entity.getClass();
        if (!residents.containsKey(clazz)) {
            List<Entity> entitiesList = new ArrayList<>();
            entitiesList.add(entity);
            residents.put(clazz, entitiesList);
        } else {
            List<Entity> entitiesList = residents.get(clazz);
            entitiesList.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        Class<? extends Entity> clazz = entity.getClass();
        if (residents.containsKey(clazz)) {
            List<Entity> entitiesList = residents.get(clazz);
            entitiesList.remove(entity);
        }
    }

    public boolean checkFreeSpace(Entity entity) {
        if (!residents.containsKey(entity.getClass())) {
            residents.put(entity.getClass(), new ArrayList<>());
            return true;
        }
        if (residents.get(entity.getClass()).size() < entity.getAmountMax()) {
            return true;
        }
        return false;
    }
}
