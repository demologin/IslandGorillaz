package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.Organism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class CellWorker implements Callable<Void>{
    protected final GameMap gameMap;
    protected final Cell cell;

    public CellWorker(GameMap gameMap, Cell cell) {
        this.gameMap = gameMap;
        this.cell = cell;
    }

    @Override
    public Void call() throws Exception {
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        for (List<Organism> organismList : residentMap.values()) {
            List<Organism> copyList = new ArrayList<>(organismList);
            for (Organism organism : copyList) {
                doAction(cell,organism);
            }
            copyList = null;
        }
        return null;
    }

    abstract  void doAction(Cell cell, Organism organism);
}
