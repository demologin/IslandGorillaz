package com.javarush.island.siberia2.entity.map.generators;

import java.util.Random;

public class BaseTerrainGenerator {

    private final Random random = new Random();

    public void generateBaseTerrain(TerrainType[][] terrainMap, int width, int height) {
        TerrainType[] landTiles = {TerrainType.SOIL, TerrainType.GRASS};
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TerrainType tile = landTiles[random.nextInt(landTiles.length)];
                terrainMap[y][x] = tile;
            }
        }
    }
}