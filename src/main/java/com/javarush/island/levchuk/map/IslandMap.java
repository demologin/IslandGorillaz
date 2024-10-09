package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.entitys.Entity;
import com.javarush.island.levchuk.utils.EntityFactory;
import com.javarush.island.levchuk.utils.PrototypesCreator;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class IslandMap {
    public static Cell[][] map;

    public IslandMap() {
        map = new Cell[Constants.DEFAULT_MAP_ROW][Constants.DEFAULT_MAP_COL];
    }

    IslandMap(int row, int col) {
        map = new Cell[row][col];
    }

    public void fillMapEntities() {
        EntityFactory entityFactory = new EntityFactory();
        PrototypesCreator prototypesCreator = new PrototypesCreator();
        entityFactory.registerEntity(prototypesCreator.loadPrototypes());
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Map<Class<? extends Entity>, Entity> entities = entityFactory.getEntities();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                for (Map.Entry<Class<? extends Entity>, Entity> entityItem : entities.entrySet()){
                    int amountMax = entityItem.getValue().getAmountMax();
                    int countEntitiesByType = random.nextInt(amountMax);
                    for (int i = 0; i < countEntitiesByType; i++) {
                        map[row][col].addEntity(entityFactory.copyEntity(entityItem.getValue()));
                    }
                }
            }
        }
    }

    public void createEmptyMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i, j);
            }
        }
        Arrays.stream(map).forEach(cells -> Arrays.stream(cells)
                .forEach(cell -> cell.findNeighbors(map)));
    }
}
