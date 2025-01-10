package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;

import java.util.concurrent.Callable;

public abstract class CellWorker implements Callable<Void> {
    protected final GameMap gameMap;
    protected final Cell cell;

    public CellWorker(GameMap gameMap, Cell cell) {
        this.gameMap = gameMap;
        this.cell = cell;
    }
}
