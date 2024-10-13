package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.config.Constants;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ObjectLayer {
    private final TileManager rockManager;
    private final TileManager treeManager;
    private final TileManager wheatManager;
    private final int tileSize;
    private final int scale;
    private final Random random = new Random();

    public ObjectLayer(TileManager rockManager, TileManager treeManager, TileManager wheatManager, int tileSize, int scale) {
        this.rockManager = rockManager;
        this.treeManager = treeManager;
        this.wheatManager = wheatManager;
        this.tileSize = tileSize;
        this.scale = scale;
    }

    public int[][] generateObjectLayer(int[][] worldMap, int islandWidth, int islandHeight) {
        int[][] objectLayer = new int[islandHeight][islandWidth];

        placeRock(objectLayer, worldMap, islandWidth, islandHeight);
        generateForest(objectLayer, worldMap, islandWidth, islandHeight);
        generateWheatField(objectLayer, worldMap, islandWidth, islandHeight);

        return objectLayer;
    }

    private void placeRock(int[][] objectLayer, int[][] worldMap, int islandWidth, int islandHeight) {
        for (int row = 0; row < islandHeight; row++) {
            for (int col = 0; col < islandWidth; col++) {
                if (worldMap[row][col] == TileFiller.GRASS_TILE || worldMap[row][col] == TileFiller.SOIL_TILE) {
                    int objectChance = random.nextInt(100);
                    if (objectChance < 3) {
                        objectLayer[row][col] = random.nextInt(rockManager.getTileCount());
                    } else {
                        objectLayer[row][col] = -1;
                    }
                } else {
                    objectLayer[row][col] = -1;
                }
            }
        }
    }

    private void generateForest(int[][] objectLayer, int[][] worldMap, int islandWidth, int islandHeight) {
        int forestCount = Constants.FOREST_COUNT + random.nextInt(3);
        int forestSize = Constants.FOREST_SIZE + random.nextInt(3);

        for (int i = 0; i < forestCount; i++) {
            int centerRow = random.nextInt(islandHeight);
            int centerCol = random.nextInt(islandWidth);

            for (int row = Math.max(0, centerRow - forestSize); row < Math.min(islandHeight, centerRow + forestSize); row++) {
                for (int col = Math.max(0, centerCol - forestSize); col < Math.min(islandWidth, centerCol + forestSize); col++) {
                    if (worldMap[row][col] == TileFiller.GRASS_TILE || worldMap[row][col] == TileFiller.SOIL_TILE) {
                        objectLayer[row][col] = 100 + random.nextInt(treeManager.getTileCount());
                    }
                }
            }
        }
    }

    private void generateWheatField(int[][] objectLayer, int[][] worldMap, int islandWidth, int islandHeight) {
        int fieldCount = Constants.FIELD_COUNT + random.nextInt(3);
        int fieldSize = Constants.FIELD_SIZE + random.nextInt(4);

        for (int i = 0; i < fieldCount; i++) {
            int centerRow = random.nextInt(islandHeight);
            int centerCol = random.nextInt(islandWidth);

            for (int row = Math.max(0, centerRow - fieldSize); row < Math.min(islandHeight, centerRow + fieldSize); row++) {
                for (int col = Math.max(0, centerCol - fieldSize); col < Math.min(islandWidth, centerCol + fieldSize); col++) {
                    if (worldMap[row][col] == TileFiller.GRASS_TILE || worldMap[row][col] == TileFiller.SOIL_TILE) {
                        objectLayer[row][col] = 200 + random.nextInt(wheatManager.getTileCount());
                    }
                }
            }
        }
    }

    public void renderObjectLayer(Graphics g, int[][] objectLayer, int width, int height) {
        int scaledTileSize = tileSize * scale;
        int rows = objectLayer.length;
        int cols = objectLayer[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int objectIndex = objectLayer[row][col];
                BufferedImage objectTile = null;

                if (objectIndex >= 0 && objectIndex < rockManager.getTileCount()) {
                    objectTile = rockManager.getTileByIndex(objectIndex);
                } else if (objectIndex >= 100 && objectIndex < 100 + treeManager.getTileCount()) {
                    objectTile = treeManager.getTileByIndex(objectIndex - 100);
                } else if (objectIndex >= 200 && objectIndex < 200 + wheatManager.getTileCount()) {
                    objectTile = wheatManager.getTileByIndex(objectIndex - 200);
                }

                if (objectTile != null) {
                    g.drawImage(objectTile, col * scaledTileSize, row * scaledTileSize, scaledTileSize, scaledTileSize, null);
                }
            }
        }
    }

}