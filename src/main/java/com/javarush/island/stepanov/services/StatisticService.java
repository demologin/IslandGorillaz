package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.SortedByValueTreeMap;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.security.KeyStore;
import java.util.*;

import static com.javarush.island.stepanov.constants.Constants.FIRST_NUMBRER;
import static com.javarush.island.stepanov.constants.Constants.MAX_PERCENT;

public class StatisticService {
    public void calcStatisticsOnCell(Cell cell) {
        SortedByValueTreeMap<String,Integer> statisticMap = cell.getPopulationStatistics();
        statisticMap.clear();
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        for (Map.Entry<String, List<Organism>> entry : residentMap.entrySet()) {
            String name = entry.getKey(); // Ключ
            List<Organism> organisms = entry.getValue();
            if (!organisms.isEmpty()) {
                Integer percentOfFilling = getPercentOfFilling(organisms);
                statisticMap.put(name, percentOfFilling);
            }
        }
    }

    private Integer getPercentOfFilling(List<Organism> organisms) {
        Organism prototype = organisms.get(FIRST_NUMBRER);
        int numberOfFlocks= organisms.size();
        int flockSize = prototype.getFlockSize();
        int numberOfOrganisms = numberOfFlocks*flockSize;
        int maxCountInCell = prototype.getMaxCountInCell();
        Integer percentOfFilling = MAX_PERCENT*numberOfOrganisms/maxCountInCell ;
        return percentOfFilling;
    }

}
