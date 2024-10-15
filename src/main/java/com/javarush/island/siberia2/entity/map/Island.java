package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.config.Constants;
import lombok.Getter;

public class Island {
    @Getter
    private final int width;
    @Getter
    private final int height;
    private final Cell[][] cells;
    private final MapData mapData;

    public Island(int width, int height, MapData mapData) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        this.mapData = mapData;
        initializeCells();
    }

    private void initializeCells() {
        int[][] terrainMap = mapData.getTerrainMap();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileIndex = terrainMap[y][x];
                boolean isWater = (tileIndex == MapDataTerrain.WATER_TILE);
                cells[y][x] = new Cell(x, y, isWater, this);            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException(Constants.ISLAND_OUT_IF_ISLAND + x + y);
        }
        return cells[y][x];
    }

}