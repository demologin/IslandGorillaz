package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.organisms.OrganismService;

public class LimitWorker extends CellWorker {

    public LimitWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    void doAction(Cell cell, Organism organism) {
        OrganismService organismService = (OrganismService) organism;
        organismService.limitOrganisms(cell);
    }
}
