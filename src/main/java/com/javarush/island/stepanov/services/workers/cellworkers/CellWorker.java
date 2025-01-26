package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public abstract class CellWorker implements Callable<Void> {
    protected final GameMap gameMap;
    protected final Cell cell;

    public CellWorker(GameMap gameMap, Cell cell) {
        this.gameMap = gameMap;
        this.cell = cell;
    }

    @Override
    public Void call() throws Exception {
        Set<String> organismsSet = cell.getOrganismsSet();
        for (String organismName : organismsSet) {
            List<Organism> organismList = cell.getOrganisms(organismName);
            List<Organism> copyList = new ArrayList<>(organismList);
            Collections.shuffle(copyList);
            for (Organism organism : copyList) {
                doAction(cell, organism);
            }
            copyList = null;
        }
        return null;
    }

    abstract void doAction(Cell cell, Organism organism);
}
