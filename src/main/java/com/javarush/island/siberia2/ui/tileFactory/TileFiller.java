package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.config.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TileFiller {
    private final TileManager tileManager;
    private final int tileSize;
    private final int scale;
    private final Random random = new Random();

    public static final int WATER_TILE = 0;
    public static final int SOIL_TILE = 1;
    public static final int GRASS_TILE = 2;
    public static final int PATH_TILE_1 = 3;
    public static final int PATH_TILE_2 = 4;

    public TileFiller(TileManager tileManager, int tileSize, int scale) {
        this.tileManager = tileManager;
        this.tileSize = tileSize;
        this.scale = scale;
    }

    public int[][] generateWorld(int islandWidth, int islandHeight) {
        int[][] worldMap = new int[islandHeight][islandWidth];

        generateBaseTerrain(worldMap, islandHeight, islandWidth);
        generateWater(worldMap, islandHeight, islandWidth);
        generatePaths(worldMap, islandHeight, islandWidth);

        return worldMap;
    }

    public void renderWorld(Graphics g, int[][] worldMap, int width, int height) {
        int scaledTileSize = tileSize * scale;
        int cols = Math.min(worldMap[0].length, width / scaledTileSize);
        int rows = Math.min(worldMap.length, height / scaledTileSize);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int tileIndex = worldMap[row][col];
                BufferedImage tile = tileManager.getTileByIndex(tileIndex);

                g.drawImage(tile, col * scaledTileSize, row * scaledTileSize, scaledTileSize, scaledTileSize, null);
            }
        }
    }

    private void generateBaseTerrain(int[][] worldMap, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                //landscape probability for grass 70 and 30 for soil
                worldMap[row][col] = random.nextInt(100) < 70 ? GRASS_TILE : SOIL_TILE;
            }
        }
    }

    private void generateWater(int[][] worldMap, int rows, int cols) {

        int riverCount = Constants.RIVER_COUNT + random.nextInt(3);
        for (int i = 0; i < riverCount; i++) {
            int riverStart = random.nextInt(cols);
            int currentCol = riverStart;
            for (int row = 0; row < rows; row++) {
                // Рисуем реку шириной 1 блок
                worldMap[row][currentCol] = WATER_TILE;
                if (currentCol > 0) {
                    worldMap[row][currentCol - 1] = WATER_TILE;
                }
                if (currentCol < cols - 1) {
                    worldMap[row][currentCol + 1] = WATER_TILE;
                }

                int direction = random.nextInt(3);
                if (direction == 0 && currentCol > 0) {
                    currentCol--;
                } else if (direction == 1 && currentCol < cols - 1) {
                    currentCol++;
                }
            }
        }

        int lakeCount = Constants.LAKE_COUNT + random.nextInt(3);
        for (int i = 0; i < lakeCount; i++) {
            int lakeRow = random.nextInt(rows);
            int lakeCol = random.nextInt(cols);
            for (int r = lakeRow; r < Math.min(lakeRow + 3, rows); r++) {
                for (int c = lakeCol; c < Math.min(lakeCol + 3, cols); c++) {
                    worldMap[r][c] = WATER_TILE;
                }
            }
        }
    }

    private void generatePaths(int[][] worldMap, int rows, int cols) {
        int pathCount = Constants.ROAD_COUNT + random.nextInt(3);

        for (int i = 0; i < pathCount; i++) {
            int pathStart = random.nextInt(rows);
            int currentRow = pathStart;
            for (int col = 0; col < cols; col++) {
                if (worldMap[currentRow][col] == WATER_TILE) {
                    worldMap[currentRow][col] = random.nextBoolean() ? PATH_TILE_1 : PATH_TILE_2;
                } else {
                    worldMap[currentRow][col] = random.nextBoolean() ? PATH_TILE_1 : PATH_TILE_2;
                }
                if (currentRow > 0 && col > 0 && worldMap[currentRow - 1][col - 1] == PATH_TILE_1) {
                    worldMap[currentRow][col - 1] = PATH_TILE_1;
                }
                int direction = random.nextInt(4);
                if (direction == 0 && currentRow > 0) {
                    currentRow--;
                } else if (direction == 1 && currentRow < rows - 1) {
                    currentRow++;
                }
            }
        }
    }

}