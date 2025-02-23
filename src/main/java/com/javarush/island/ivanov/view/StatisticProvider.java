package com.javarush.island.ivanov.view;

import com.javarush.island.ivanov.constants.ConsoleMessages;
import com.javarush.island.ivanov.entity.map.Cell;
import com.javarush.island.ivanov.entity.map.GameMap;
import com.javarush.island.ivanov.entity.organism.Organism;
import com.javarush.island.ivanov.utils.OrganismsFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class StatisticProvider {
    public void printTextStatistic(GameMap islandMap, ConsoleProvider console) {
        Map<String, Integer> map = new TreeMap<>();
        Cell[][] cells = islandMap.getIslandMap();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Map<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> entities = cell.getResidents();
                for (Map.Entry<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> entry : entities.entrySet()) {
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
        console.println(ConsoleMessages.ISLAND_STATISTIC);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            console.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printByCell(GameMap islandMap, ConsoleProvider console) {
        Map<String, StringBuilder> mapSb = new TreeMap<>();
        Map<Class<? extends Organism>, Organism> map = OrganismsFactory.getOrganisms();
        mapSb.put("!loc", new StringBuilder());
        for (Map.Entry<Class<? extends Organism>, Organism> item : map.entrySet()) {
            mapSb.put(item.getValue().getIcon(), new StringBuilder());
        }
        Cell[][] locations = islandMap.getIslandMap();
        for (int j = 0; j < locations[0].length; j++) {
            for (int i = 0; i < locations.length; i++) {
                Map<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> organisms = locations[i][j].getResidents();
                if (!mapSb.containsKey("!loc")) {
                    mapSb.put("!loc", new StringBuilder("|- " + i + "," + j + " -|"));
                } else {
                    mapSb.put("!loc", mapSb.get("!loc").append(" ").append("|- ").append(i).append(",").append(j).append(" -|"));
                }
                int length = mapSb.get("!loc").length();
                for (Map.Entry<Class<? extends Organism>, CopyOnWriteArrayList<Organism>> organism : organisms.entrySet()) {
                    String key;
                    if (!organism.getValue().isEmpty()) {
                        try {
                            key = (String) organism.getKey().getMethod("getIcon").invoke(organism.getValue().get(0));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                        int value = organism.getValue().size();
                        mapSb.put(key, mapSb.get(key).append(" ").append(key).append(" ").append(value));
                    }
                }
                for (Map.Entry<String, StringBuilder> map2 : mapSb.entrySet()) {
                    while (map2.getValue().length() < length) {
                        map2.getValue().append(" ");
                    }
                }
            }
            mapSb.forEach((k, v) -> console.println(v));
            for (Map.Entry<String, StringBuilder> map2 : mapSb.entrySet()) {
                map2.getValue().setLength(0);
            }
        }
    }
}
