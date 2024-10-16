package com.javarush.island.levchuk.utils;

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
        return new Cell[getMapSizeX()][getMapSizeY()];
    }

    private int getMapSizeX() {
        return getMapDimension("X", Constants.DEFAULT_MAP_ROW, console);
    }

    private int getMapSizeY() {
        return getMapDimension("Y", Constants.DEFAULT_MAP_COL, console);
    }

    private int getMapDimension(String dimension, int maxSizeDimension, ConsoleProvider console) {
        console.print("Enter Island " + dimension + " size :");
        String inputLine = console.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= maxSizeDimension) {
                    return size;
                }
                throw new IllegalArgumentException("Invalid input size. Check input data. ");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input is not a number. Check input data. ");
            }
        }
        throw new IllegalArgumentException("Invalid input data. Check input data. ");
    }

}
