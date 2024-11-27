package com.javarush.island.popov.map;

import com.javarush.island.popov.units.Unit;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private final int row;
    private final int col;
    @Getter
    private Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> allUnitsInCell;
    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        allUnitsInCell = new LinkedHashMap<>();
    }

    @Getter
    private final Lock lock = new ReentrantLock();
    @Getter
    private final List<Cell> nextCell = new ArrayList<>();

    public void updateNextCell(IslandMap map, int y, int x) {
        Cell[][] cells = map.getCells();
        if (y > 0) nextCell.add(cells[y - 1][x]);
        if (x > 0) nextCell.add(cells[y][x - 1]);
        if (y < map.getY() - 1) nextCell.add(cells[y + 1][x]);
        if (x < map.getX() - 1) nextCell.add(cells[y][x+1]);
    }

    public Cell findNextCell(int numberSteps) {
        Cell currentCell = this;
        Set<Cell> visitedCells = new LinkedHashSet<>();
        while (numberSteps >0){
            var nextCells = currentCell.nextCell
                    .stream()
                    .filter(cell -> !visitedCells.contains(cell))
                    .toList();
            int numbersDirection = nextCells.size();
            if (numbersDirection >0){
                int index = ThreadLocalRandom.current().nextInt(0, numbersDirection);
                visitedCells.add(currentCell);
                currentCell = nextCells.get(nextCells.size()-1-index);
                numberSteps--;
            } else break;
        }
        return currentCell;
    }
    public void addUnit(Unit unit){
        Class<? extends Unit> clazz = unit.getClass();
        if(allUnitsInCell.containsKey(clazz)){
            CopyOnWriteArrayList<Unit> units = allUnitsInCell.get(clazz);
            units.add(unit);
        }else {
            CopyOnWriteArrayList<Unit> units = new CopyOnWriteArrayList<>();
            units.add(unit);
            allUnitsInCell.put(clazz, units);
        }
    }
    public void removeUnit(Unit unit){
        Class<? extends Unit> clazz = unit.getClass();
        if (checkUnitInCell(unit)){
            CopyOnWriteArrayList<Unit> units = allUnitsInCell.get(clazz);
            units.remove(unit);
        }
    }
    public boolean checkUnitInCell(Unit unit){
        Class<? extends Unit> clazz = unit.getClass();
        if (allUnitsInCell.containsKey(clazz)){
            CopyOnWriteArrayList<Unit> units = allUnitsInCell.get(clazz);
            return units.size()!=0 ? true:false;
        }else return false;
    }
public boolean haveEnoughtSpace(Unit unit){
        if (!allUnitsInCell.containsKey(unit.getClass())){
           return true;
        }else return allUnitsInCell.get(unit.getClass()).size() < unit.getMaxUnitsInCell();
}
    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", lock=" + lock +
                ", unitsInCell=" + allUnitsInCell +
                ", nextCell=" + nextCell +
                '}';
    }
}

