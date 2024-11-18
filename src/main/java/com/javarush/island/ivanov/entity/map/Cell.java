package com.javarush.island.ivanov.entity.map;



import com.javarush.island.ivanov.entity.organism.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


@Getter
@Setter
public class Cell {
    private final int row;
    private final int col;
    private Set<Cell> neighbors;
    private Map<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> residents = new HashMap<>();

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void findNeighbours(Cell[][] array){
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

    public void addEntity(Organism entity) {
        Class<? extends Organism> clazz = entity.getClass();
        if (!residents.containsKey(clazz)) {
            CopyOnWriteArrayList<Organism> entitiesList = new CopyOnWriteArrayList<>();
            entitiesList.add(entity);
            residents.put(clazz, entitiesList);
        } else {
            List<Organism> entitiesList = residents.get(clazz);
            entitiesList.add(entity);
        }
    }

    public void removeEntity(Organism entity) {
        Class<? extends Organism> clazz = entity.getClass();
        if (residents.containsKey(clazz)) {
            List<Organism> entitiesList = residents.get(clazz);
            entitiesList.remove(entity);
        }
    }

    public boolean checkFreeSpace(Organism entity) {
        if (!residents.containsKey(entity.getClass())) {
            return true;
        }
        if (residents.get(entity.getClass()).size() < entity.getAmountMax()) {
            return true;
        }
        return false;
    }
}


