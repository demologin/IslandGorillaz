package com.javarush.island.siberia2.entity.map.generators;

import com.javarush.island.siberia2.config.Constants;
import java.util.Random;

public class PathGenerator {

    private final Random random = new Random();

    public void generatePaths(TerrainType[][] terrainMap, int width, int height) {
        int roadCount = Constants.ROAD_COUNT;
        for (int i = 0; i < roadCount; i++) {
            int x = 0;
            int y = random.nextInt(height);
            int pathWidth = random.nextInt(2) + 1;
            for (int j = 0; j < width; j++) {
                for (int w = -pathWidth / 2; w <= pathWidth / 2; w++) {
                    int dy = y + w;
                    if (isInBounds(x, dy, width, height)) {
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

    private boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}