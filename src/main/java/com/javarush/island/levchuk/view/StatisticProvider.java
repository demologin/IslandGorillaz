package com.javarush.island.levchuk.view;

import com.javarush.island.levchuk.constants.ConsoleMessages;
import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;
import com.javarush.island.levchuk.utils.EntityFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class StatisticProvider {
    public void printTextStatistic(IslandMap islandMap, ConsoleProvider console) {
        Map<String, Integer> map = new TreeMap<>();
        Cell[][] cells = islandMap.getIslandMap();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Map<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> entities = cell.getResidents();
                for (Map.Entry<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> entry : entities.entrySet()) {
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

    public void printByCell(IslandMap islandMap, ConsoleProvider console) {
        Map<String, StringBuilder> stringStringBuilderMap = new TreeMap<>();
        Map<Class<? extends Entity>, Entity> map = EntityFactory.getEntities();
        stringStringBuilderMap.put("!loc", new StringBuilder());
        for (Map.Entry<Class<? extends Entity>, Entity> item : map.entrySet()) {
            stringStringBuilderMap.put(item.getValue().getIcon(), new StringBuilder());
        }
        Cell[][] locations = islandMap.getIslandMap();
        for (int j = 0; j < locations[0].length; j++) {
            for (int i = 0; i < locations.length; i++) {
                Map<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> organisms = locations[i][j].getResidents();
                if (!stringStringBuilderMap.containsKey("!loc")) {
                    stringStringBuilderMap.put("!loc", new StringBuilder("|- " + i + "," + j + " -|"));
                } else {
                    stringStringBuilderMap.put("!loc", stringStringBuilderMap.get("!loc").append(" ").append("|- ").append(i).append(",").append(j).append(" -|"));
                }
                int length = stringStringBuilderMap.get("!loc").length();
                for (Map.Entry<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> organism : organisms.entrySet()) {
                    String key;
                    if (!organism.getValue().isEmpty()) {
                        try {
                            key = (String) organism.getKey().getMethod("getIcon").invoke(organism.getValue().get(0));
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                        int value = organism.getValue().size();
                        stringStringBuilderMap.put(key, stringStringBuilderMap.get(key).append(" ").append(key).append(" ").append(value));
                    }
                }
                for (Map.Entry<String, StringBuilder> map2 : stringStringBuilderMap.entrySet()) {
                    while (map2.getValue().length() < length) {
                        map2.getValue().append(" ");
                    }
                }
            }
            stringStringBuilderMap.forEach((k, v) -> console.println(v));
            for (Map.Entry<String, StringBuilder> map2 : stringStringBuilderMap.entrySet()) {
                map2.getValue().setLength(0);
            }
        }
    }
}
