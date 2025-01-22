package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.map.GeneralStatisticsMap;
import com.javarush.island.stepanov.entity.map.SortedByValueTreeMap;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.util.*;

import static com.javarush.island.stepanov.constants.Constants.FIRST_NUMBRER;
import static com.javarush.island.stepanov.constants.Constants.MAX_PERCENT;

public class StatisticService {
    private GameMap gameMap;
    private GeneralStatisticsMap generalStatisticsMap = gameMap.getGeneralStatisticsMap();

    public StatisticService(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void calcStatisticsOnCell(Cell cell) {
        SortedByValueTreeMap<String,Integer> statisticMap = cell.getPopulationStatistics();
        statisticMap.clear();
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        for (Map.Entry<String, List<Organism>> entry : residentMap.entrySet()) {
            String name = entry.getKey(); // Ключ
            List<Organism> organisms = entry.getValue();
            if (!organisms.isEmpty()) {
                Integer percentOfFilling = countingOneSpecies(organisms);
                statisticMap.put(name, percentOfFilling);
            }
        }
    }

    private Integer countingOneSpecies(List<Organism> organisms) {
        Organism prototype = organisms.get(FIRST_NUMBRER);
        String name = prototype.getName();
        int numberOfFlocks= organisms.size();
        int flockSize = prototype.getFlockSize();
        int numberOfOrganisms = numberOfFlocks*flockSize;
        int maxCountInCell = prototype.getMaxCountInCell();
        Integer percentOfFilling = MAX_PERCENT*numberOfOrganisms/maxCountInCell ;
        generalStatisticsMap.addValue(name, numberOfOrganisms);
        return percentOfFilling;
    }

}
