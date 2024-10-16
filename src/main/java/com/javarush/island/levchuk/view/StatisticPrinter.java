package com.javarush.island.levchuk.view;

import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticPrinter {
    public void printTextStatistic(IslandMap islandMap, ConsoleProvider console) {
        Map<String, Integer> map = new TreeMap<>();
        Cell[][] cells =  islandMap.getIslandMap();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Map<Class<? extends Entity>, List<Entity>> entities = cell.getResidents();
                for (Map.Entry<Class<? extends Entity>, List<Entity>> entry : entities.entrySet()) {
                    String entityName = entry.getKey().getSimpleName();
                    int numberEntity = entry.getValue().size();
                    if (!map.containsKey(entityName)) {
                        map.put(entityName, numberEntity);
                    } else {
                        map.put(entityName, map.get(entityName) + numberEntity);
                    }
                }
            }
        }
        console.println("*** Area statistic ***");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            console.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printIconsMap(IslandMap islandMap, ConsoleProvider console) {
        Cell[][] cells =  islandMap.getIslandMap();
        for (Cell[] row : cells) {
            System.out.println();
            for (Cell cell : row) {
                Map<Class<? extends Entity>, List<Entity>> entities = cell.getResidents();
                for (Map.Entry<Class<? extends Entity>, List<Entity>> entry : entities.entrySet()) {
                    entry.getValue().stream().forEach(entity -> {
                        if (entity != null) {
                            System.out.print(entity.getIcon());
                        }
                    });
                }
            }
        }
    }
}
