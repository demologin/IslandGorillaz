package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.map.GeneralStatistic;
import com.javarush.island.stepanov.entity.map.SortedByValueTreeMap;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.util.List;
import java.util.Set;

import static com.javarush.island.stepanov.constants.Constants.FIRST_NUMBRER;
import static com.javarush.island.stepanov.constants.Constants.MAX_PERCENT;

public class StatisticService {
    private GameMap gameMap;
    private GeneralStatistic generalStatisticsMap;

    public StatisticService(GameMap gameMap) {
        this.gameMap = gameMap;
        this.generalStatisticsMap = gameMap.getGeneralStatisticsMap();
    }

    public void calcStatisticsOnCell(Cell cell) {
        SortedByValueTreeMap<String, Integer> statisticMap = cell.getPopulationStatistics();
        statisticMap.clear();
        Set<String> organismsSet = cell.getOrganismsSet();
        for (String organismName : organismsSet) {
            List<Organism> organisms = cell.getOrganisms(organismName);
            if (!organisms.isEmpty()) {
                Integer percentOfFilling = countingOneSpecies(organisms);
                statisticMap.put(organismName, percentOfFilling);
            }
        }
    }

    private Integer countingOneSpecies(List<Organism> organisms) {
        Organism prototype = organisms.get(FIRST_NUMBRER);
        String name = prototype.getName();
        int numberOfFlocks = organisms.size();
        int flockSize = prototype.getFlockSize();
        int numberOfOrganisms = numberOfFlocks * flockSize;
        int maxCountInCell = prototype.getMaxCountInCell();
        Integer percentOfFilling = MAX_PERCENT * numberOfOrganisms / maxCountInCell;
        generalStatisticsMap.addValue(name, numberOfOrganisms);
        return percentOfFilling;
    }

}
