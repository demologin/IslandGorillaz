package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.util.HashMap;
import java.util.List;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class MoveWorker extends CellWorker {
    public MoveWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    public Void call() throws Exception {
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        for (List<Organism> organismList : residentMap.values()) {
            for (Organism organism : organismList) {
                if (organism instanceof AnimalService) {
                    AnimalService animal = (AnimalService) organism;
                    animal.move();
                }
            }
        }
        return null; // Возвращаем null, так как Void
    }
}
