package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.config.Constants;
import lombok.Getter;
import java.util.Random;

public class MapDataTerrain {

    @Getter
    private final int[][] terrainMap;
    private final int width;
    private final int height;
    private final Random random = new Random();

    //for easy object index
    public static final int WATER_TILE = 0;
    public static final int SOIL_TILE = 1;
    public static final int GRASS_TILE = 2;
    public static final int PATH_TILE = 3;
    public static final int BRIDGE_TILE = 4;

    public MapDataTerrain(int width, int height) {
        this.width = width;
        this.height = height;
        terrainMap = new int[height][width];
        generateTerrainMap();
    }

    private void generateTerrainMap() {
        generateBaseTerrain();
        generateWater();
        generatePaths();
    }

    private void generateBaseTerrain() {
        int[] landTiles = {SOIL_TILE, GRASS_TILE};
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileIndex = landTiles[random.nextInt(landTiles.length)];
                terrainMap[y][x] = tileIndex;
            }
        }
    }

    private void generateWater() {
        int riverCount = Constants.RIVER_COUNT;
        for (int i = 0; i < riverCount; i++) {
            int x = random.nextInt(width);
            int y = 0;
            int riverWidth = random.nextInt(3) + 2;
            while (y < height) {
                for (int w = -riverWidth / 2; w <= riverWidth / 2; w++) {
                    int dx = x + w;
                    if (isInBounds(dx, y)) {
                        terrainMap[y][dx] = WATER_TILE;
                    }
                }
                y++;
                x += random.nextInt(3) - 1;
                x = Math.max(0, Math.min(x, width - 1));
            }
        }

        int lakeCount = Constants.LAKE_COUNT;
        for (int i = 0; i < lakeCount; i++) {
            int centerX = random.nextInt(width);
            int centerY = random.nextInt(height);
            int lakeSize = random.nextInt(4) + 3;
            for (int y = centerY - lakeSize; y <= centerY + lakeSize; y++) {
                for (int x = centerX - lakeSize; x <= centerX + lakeSize; x++) {
                    if (isInBounds(x, y)) {
                        if (Math.hypot(centerX - x, centerY - y) <= lakeSize) {
                            terrainMap[y][x] = WATER_TILE;
                        }
                    }
                }
            }
        }
    }

    private void generatePaths() {
        int roadCount = Constants.ROAD_COUNT;
        for (int i = 0; i < roadCount; i++) {
            int x = 0;
            int y = random.nextInt(height);
            int pathLength = width;
            int pathWidth = random.nextInt(2) + 1;
            for (int j = 0; j < pathLength; j++) {
                for (int w = -pathWidth / 2; w <= pathWidth / 2; w++) {
                    int dy = y + w;
                    if (isInBounds(x, dy)) {
                        int underlyingTile = terrainMap[dy][x];
                        int pathTile;
                        if (underlyingTile == WATER_TILE) {
                            pathTile = BRIDGE_TILE;
                        } else {
                            pathTile = PATH_TILE;
                        }

                        terrainMap[dy][x] = pathTile;
                    }
                }
                x++;
                y += random.nextInt(3) - 1;
                y = Math.max(0, Math.min(y, height - 1));
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getTile(int x, int y) {
        return terrainMap[y][x];
    }

}