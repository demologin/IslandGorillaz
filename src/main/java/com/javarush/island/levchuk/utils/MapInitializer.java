package com.javarush.island.levchuk.utils;

import com.javarush.island.levchuk.constants.ConsoleMessages;
import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.view.ConsoleProvider;


import java.util.Map;

public class MapInitializer {
    public final ConsoleProvider console;

    public MapInitializer(ConsoleProvider console) {
        this.console = console;
    }

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

    public void fillMapEntities(Cell[][] map, EntityFactory entityFactory, PrototypesCreator prototypesCreator) {
        createEmptyMap(map);
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

    public Cell[][] getMapArray() {
        return new Cell[getMapRows()][getMapCols()];
    }

    private int getMapRows() {
        return getMapDimension("X", Constants.MAX_MAP_ROW, console);
    }

    private int getMapCols() {
        return getMapDimension("Y", Constants.MAX_MAP_COL, console);
    }

    private int getMapDimension(String dimension, int maxSize, ConsoleProvider console) {
        console.print("Enter Island " + dimension + " size (1:" + maxSize + "):");
        String inputLine = console.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= maxSize) {
                    return size;
                }
                throw new IllegalArgumentException(ConsoleMessages.INVALID_INPUT_SIZE);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ConsoleMessages.NOT_A_NUMBER, e);
            }
        }
        throw new IllegalArgumentException(ConsoleMessages.INVALID_INPUT_DATA);
    }

}
