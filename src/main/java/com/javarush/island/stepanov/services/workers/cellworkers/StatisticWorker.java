package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.StatisticService;

public class StatisticWorker extends CellWorker {
    StatisticService statisticService = new StatisticService(gameMap);

    public StatisticWorker(GameMap gameMap, Cell cell) {
        super(gameMap, cell);
    }

    @Override
    public Void call() throws Exception {
        statisticService.calcStatisticsOnCell(cell);
        return null;
    }

    @Override
    void doAction(Cell cell, Organism organism) {

    }
}
