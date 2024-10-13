package com.javarush.island.levchuk.utils;

import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;

import java.util.Arrays;
import java.util.Map;

public class MapInitializer {
    public void createEmptyMap(Cell[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i, j);
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].findNeighbors(map);
            }
        }
    }
    public void fillMapEntities(Cell[][] map) {
        EntityFactory entityFactory = new EntityFactory();
        PrototypesCreator prototypesCreator = new PrototypesCreator();
        entityFactory.registerEntity(prototypesCreator.loadPrototypes());
        Map<Class<? extends Entity>, Entity> entities = EntityFactory.getEntities();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                for (Map.Entry<Class<? extends Entity>, Entity> entityItem : entities.entrySet()) {
                    int amountMax = entityItem.getValue().getAmountMax();
                    int countEntitiesByType = Randomizer.getRandomInt(amountMax);
                    for (int i = 0; i < countEntitiesByType; i++) {
                        map[row][col].addEntity(entityFactory.copyEntity(entityItem.getValue()));
                    }
                }
            }
        }
    }


}
