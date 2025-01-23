package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.oganism.Organism;

public class EatWorker extends CellWorker {
    public EatWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    void doAction(Cell cell, Organism organism) {
        organism.eat(cell);
    }
}
