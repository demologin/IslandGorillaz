package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.Organism;

public class StarveWorker extends CellWorker{

    public StarveWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    void doAction(Cell cell, Organism organism) {
        organism.starve(cell);
    }
}
