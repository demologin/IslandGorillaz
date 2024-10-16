package com.javarush.island.levchuk.view;

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
        console.println("*** Area statistic ***");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            console.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printIconsMap(IslandMap islandMap, ConsoleProvider console) {
        Cell[][] cells = islandMap.getIslandMap();
        for (Cell[] row : cells) {
            System.out.println();
            for (Cell cell : row) {
                Map<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> entities = cell.getResidents();
                for (Map.Entry<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> entry : entities.entrySet()) {
                    if(entry.getValue().size() > 0) {
                        System.out.print((entry.getValue().getFirst().getIcon() + " " + entry.getValue().size()));
                    }
                }

            }
        }
    }

    public void printByCell(IslandMap islandMap, ConsoleProvider console) {
        Map<String, StringBuilder> mapSb = new TreeMap<>();
        Map<Class<? extends Entity>, Entity> map = EntityFactory.getEntities();
        mapSb.put("!loc", new StringBuilder());
        for (Map.Entry<Class<? extends Entity>, Entity> item : map.entrySet()) {
            mapSb.put(item.getValue().getIcon(), new StringBuilder());
        }
        Cell[][] locations = islandMap.getIslandMap();
        for (int j = 0; j < locations[0].length; j++) {
            for (int i = 0; i < locations.length; i++) {
                Map<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> organisms = locations[i][j].getResidents();
                if (!mapSb.containsKey("!loc")) {
                    mapSb.put("!loc", new StringBuilder("|- " + i + "," + j + " -|"));
                } else {
                    mapSb.put("!loc", mapSb.get("!loc").append(" ").append("|- ").append(i).append(",").append(j).append(" -|"));
                }
                int length = mapSb.get("!loc").length();
                for (Map.Entry<Class<? extends Entity>, CopyOnWriteArrayList<Entity>> organism : organisms.entrySet()) {
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
