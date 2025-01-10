package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.util.HashMap;
import java.util.List;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class EatWorker extends CellWorker {
    public EatWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    public Void call() throws Exception {
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        for (List<Organism> organismList : residentMap.values()) {
            for (Organism organism : organismList) {
                organism.eat();
            }
        }
        return null; // Возвращаем null, так как Void
    }
}
