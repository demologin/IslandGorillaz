package com.javarush.island.siberia2.entity.map.generators;

import com.javarush.island.siberia2.config.Constants;
import java.util.Random;

public class WaterGenerator {

    private final Random random = new Random();

    public void generateWater(TerrainType[][] terrainMap, int width, int height) {
        generateRivers(terrainMap, width, height);
        generateLakes(terrainMap, width, height);
    }

    private void generateRivers(TerrainType[][] terrainMap, int width, int height) {
        int riverCount = Constants.RIVER_COUNT;
        for (int i = 0; i < riverCount; i++) {
            int x = random.nextInt(width);
            int y = 0;
            int riverWidth = random.nextInt(3) + 2;
            while (y < height) {
                for (int w = -riverWidth / 2; w <= riverWidth / 2; w++) {
                    int dx = x + w;
                    if (isInBounds(dx, y, width, height)) {
                        terrainMap[y][dx] = TerrainType.WATER;
                    }
                }
                y++;
                x += random.nextInt(3) - 1;
                x = Math.max(0, Math.min(x, width - 1));
            }
        }
    }

    private void generateLakes(TerrainType[][] terrainMap, int width, int height) {
        int lakeCount = Constants.LAKE_COUNT;
        for (int i = 0; i < lakeCount; i++) {
            int centerX = random.nextInt(width);
            int centerY = random.nextInt(height);
            int lakeSize = random.nextInt(4) + 3;
            for (int y = centerY - lakeSize; y <= centerY + lakeSize; y++) {
                for (int x = centerX - lakeSize; x <= centerX + lakeSize; x++) {
                    if (isInBounds(x, y, width, height)) {
                        if (Math.hypot(centerX - x, centerY - y) <= lakeSize) {
                            terrainMap[y][x] = TerrainType.WATER;
                        }
                    }
                }
            }
        }
    }

    private boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}