package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;

import java.security.KeyStore;
import java.util.*;

public class StatisticService {
    public void calcStatisticsOnCell(Cell cell) {
        SortedMap<Integer,String> statisticMap = cell.getPopulationStatistics();
        statisticMap.clear();
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        for (Map.Entry<String, List<Organism>> entry : residentMap.entrySet()) {
            String name = entry.getKey(); // Ключ
            List<Organism> organisms = entry.getValue();
            if (!organisms.isEmpty()) {
                Integer percentOfFilling = getPercentOfFilling(organisms);
                statisticMap.put(percentOfFilling, name);
            }
        }
    }

    private Integer getPercentOfFilling(List<Organism> organisms) {
        int numberOfOrganisms = organisms.size();
        int maxCountInCell = organisms.get(0).getMaxCountInCell();
        Integer percentOfFilling = 100*numberOfOrganisms/maxCountInCell ;
        return percentOfFilling;
    }

}
