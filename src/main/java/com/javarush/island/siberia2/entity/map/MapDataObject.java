package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.map.generators.ObjectType;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import lombok.Getter;
import java.util.Random;

public class MapDataObject {

    @Getter
    private final ObjectType[][] objectMap;
    private final int width;
    private final int height;
    private final Random random = new Random();
    private final MapDataTerrain terrainData;

    public MapDataObject(int width, int height, MapDataTerrain terrainData) {
        this.width = width;
        this.height = height;
        this.terrainData = terrainData;
        objectMap = new ObjectType[height][width];
        generateObjectMap();
    }

    private void generateObjectMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                objectMap[y][x] = ObjectType.NO_OBJECT;
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
                objectMap[y][x] = ObjectType.ROCK;
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
                    if (isValidPlacement(x, y) && isAdjacentToObject(x, y, ObjectType.TREE)) {
                        if (random.nextInt(100) < 80) {
                            objectMap[y][x] = ObjectType.WHEAT;
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
                    if (isValidPlacement(x, y) && isAdjacentToObject(x, y, ObjectType.WHEAT)) {
                        if (random.nextInt(100) < 70) {
                            objectMap[y][x] = ObjectType.TREE;
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

        TerrainType tile = terrainData.getTile(x, y);
        ObjectType object = objectMap[y][x];

        return tile != TerrainType.WATER
                && tile != TerrainType.PATH
                && tile != TerrainType.BRIDGE
                && object == ObjectType.NO_OBJECT;
    }

    private boolean isAdjacentToObject(int x, int y, ObjectType objectType) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;
                if (isInBounds(nx, ny)) {
                    if (objectMap[ny][nx] == objectType) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}