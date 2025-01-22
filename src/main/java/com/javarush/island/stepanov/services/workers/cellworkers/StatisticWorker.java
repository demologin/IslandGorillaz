package com.javarush.island.stepanov.services.workers.cellworkers;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.map.GeneralStatisticsMap;
import com.javarush.island.stepanov.entity.map.SortedByValueTreeMap;
import com.javarush.island.stepanov.services.StatisticService;

import java.util.Map;

import static com.javarush.island.stepanov.config.Setting.ORGANISMS_VIEW_MAP;

public class StatisticWorker extends CellWorker {
    StatisticService statisticService = new StatisticService(gameMap);

    public StatisticWorker(GameMap gameMap, Cell cell) {
        super(gameMap,cell);
    }

    @Override
    public Void call() throws Exception {
        statisticService.calcStatisticsOnCell(cell);
        return null;
    }
}
