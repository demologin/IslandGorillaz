package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import lombok.Getter;
import java.util.Random;

public class MapDataTerrain {

    @Getter
    private final TerrainType[][] terrainMap;
    private final int width;
    private final int height;
    private final Random random = new Random();

    public MapDataTerrain(int width, int height) {
        this.width = width;
        this.height = height;
        terrainMap = new TerrainType[height][width];
        generateTerrainMap();
    }

    private void generateTerrainMap() {
        generateBaseTerrain();
        generateWater();
        generatePaths();
    }

    private void generateBaseTerrain() {
        TerrainType[] landTiles = {TerrainType.SOIL, TerrainType.GRASS};
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TerrainType tile = landTiles[random.nextInt(landTiles.length)];
                terrainMap[y][x] = tile;
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
                        terrainMap[y][dx] = TerrainType.WATER;
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
                            terrainMap[y][x] = TerrainType.WATER;
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
            int pathWidth = random.nextInt(2) + 1;
            for (int j = 0; j < width; j++) {
                for (int w = -pathWidth / 2; w <= pathWidth / 2; w++) {
                    int dy = y + w;
                    if (isInBounds(x, dy)) {
                        TerrainType underlyingTile = terrainMap[dy][x];
                        TerrainType pathTile;
                        if (underlyingTile == TerrainType.WATER) {
                            pathTile = TerrainType.BRIDGE;
                        } else {
                            pathTile = TerrainType.PATH;
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

    public TerrainType getTile(int x, int y) {
        return terrainMap[y][x];
    }
}