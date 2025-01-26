package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.organisms.AnimalService;

public class MoveWorker extends CellWorker {
    public MoveWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    void doAction(Cell cell, Organism organism) {
        if (organism instanceof AnimalService) {
            AnimalService animal = (AnimalService) organism;
            animal.move(cell);
        }
    }
}
