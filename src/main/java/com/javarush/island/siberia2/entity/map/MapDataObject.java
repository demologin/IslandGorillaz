package com.javarush.island.siberia2.entity.map;

import static com.javarush.island.siberia2.entity.map.MapDataTerrain.*;
import com.javarush.island.siberia2.config.Constants;
import lombok.Getter;
import java.util.Random;

public class MapDataObject {

    @Getter
    private final int[][] objectMap;
    private final int width;
    private final int height;
    private final Random random = new Random();

    public static final int NO_OBJECT = -1;
    public static final int ROCK_OBJECT = 0;
    public static final int TREE_OBJECT = 100;
    public static final int WHEAT_OBJECT = 200;

    private final MapDataTerrain terrainData;

    public MapDataObject(int width, int height, MapDataTerrain terrainData) {
        this.width = width;
        this.height = height;
        this.terrainData = terrainData;
        objectMap = new int[height][width];
        generateObjectMap();
    }

    private void generateObjectMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                objectMap[y][x] = NO_OBJECT;
            }
        }
        placeRocks();
        generateFields();
        generateForests();
    }

    private void placeRocks() {
        int rockCount = (width * height) / 50;
        for (int i = 0; i < rockCount; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (isValidPlacement(x, y)) {
                objectMap[y][x] = ROCK_OBJECT;
            }
        }
    }

    private void generateFields() {
        int fieldCount = Constants.FIELD_COUNT;
        int fieldSize = Constants.FIELD_SIZE;

        for (int i = 0; i < fieldCount; i++) {
            int centerX = random.nextInt(width);
            int centerY = random.nextInt(height);

            for (int y = centerY - fieldSize; y <= centerY + fieldSize; y++) {
                for (int x = centerX - fieldSize; x <= centerX + fieldSize; x++) {
                    if (isValidPlacement(x, y) && !isAdjacentToObject(x, y, TREE_OBJECT)) {
                        if (random.nextInt(100) < 80) {
                            objectMap[y][x] = WHEAT_OBJECT;
                        }
                    }
                }
            }
        }
    }

    private void generateForests() {
        int forestCount = Constants.FOREST_COUNT;
        int forestSize = Constants.FOREST_SIZE;

        for (int i = 0; i < forestCount; i++) {
            int centerX = random.nextInt(width);
            int centerY = random.nextInt(height);

            for (int y = centerY - forestSize; y <= centerY + forestSize; y++) {
                for (int x = centerX - forestSize; x <= centerX + forestSize; x++) {
                    if (isValidPlacement(x, y) && !isAdjacentToObject(x, y, WHEAT_OBJECT)) {
                        if (random.nextInt(100) < 70) {
                            objectMap[y][x] = TREE_OBJECT;
                        }
                    }
                }
            }
        }
    }

    private boolean isValidPlacement(int x, int y) {
        if (!isInBounds(x, y)) {
            return false;
        }

        int tile = terrainData.getTile(x, y);
        int object = objectMap[y][x];

        return tile != WATER_TILE
                && tile != PATH_TILE
                && tile != BRIDGE_TILE
                && object == NO_OBJECT;
    }

    private boolean isAdjacentToObject(int x, int y, int objectType) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;
                if (isInBounds(nx, ny)) {
                    if (objectMap[ny][nx] == objectType) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getObject(int x, int y) {
        return objectMap[y][x];
    }

}