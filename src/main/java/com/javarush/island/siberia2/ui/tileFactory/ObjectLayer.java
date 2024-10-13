package com.javarush.island.siberia2.ui.tileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ObjectLayer {
    private TileManager rockManager;
    private TileManager treeManager;
    private TileManager wheatManager;
    private int tileSize;
    private int scale;
    private Random random;

    public ObjectLayer(TileManager rockManager, TileManager treeManager, TileManager wheatManager, int tileSize, int scale) {
        this.rockManager = rockManager;
        this.treeManager = treeManager;
        this.wheatManager = wheatManager;
        this.tileSize = tileSize;
        this.scale = scale;
        this.random = new Random();
    }

    public int[][] generateObjectLayer(int[][] worldMap, int islandWidth, int islandHeight) {
        int[][] objectLayer = new int[islandHeight][islandWidth];

        for (int row = 0; row < islandHeight; row++) {
            for (int col = 0; col < islandWidth; col++) {
                if (worldMap[row][col] == TileFiller.GRASS_TILE ||
                worldMap[row][col] == TileFiller.SOIL_TILE) {
                    int objectChance = random.nextInt(100);
                    if (objectChance < 5) {
                        objectLayer[row][col] = random.nextInt(rockManager.getTileCount());
                    } else if (objectChance < 25) {
                        objectLayer[row][col] = 100 + random.nextInt(treeManager.getTileCount());
                    } else if (objectChance < 35) {
                        objectLayer[row][col] = 200 + random.nextInt(wheatManager.getTileCount());
                    } else {
                        objectLayer[row][col] = -1;
                    }
                } else {
                    objectLayer[row][col] = -1; // no objects on water or roads
                }
            }
        }
        return objectLayer;
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
                    objectTile = rockManager.getTileByIndex(objectIndex); // Камни
                } else if (objectIndex >= 100 && objectIndex < 100 + treeManager.getTileCount()) {
                    objectTile = treeManager.getTileByIndex(objectIndex - 100); // Деревья
                } else if (objectIndex >= 200 && objectIndex < 200 + wheatManager.getTileCount()) {
                    objectTile = wheatManager.getTileByIndex(objectIndex - 200); // Пшеница
                }

                if (objectTile != null) {
                    g.drawImage(objectTile, col * scaledTileSize, row * scaledTileSize, scaledTileSize, scaledTileSize, null);
                }
            }
        }
    }

}