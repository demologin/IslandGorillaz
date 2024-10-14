package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.entity.map.MapData;
import com.javarush.island.siberia2.entity.map.MapDataObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ObjectLayer {
    private final TileManager rockManager;
    private final TileManager treeManager;
    private final TileManager wheatManager;
    private final int tileSize;
    private final int scale;
    private final MapData mapData;
    private final Random random = new Random();


    public ObjectLayer(TileManager rockManager, TileManager treeManager, TileManager wheatManager, int tileSize, int scale, MapData mapData) {
        this.rockManager = rockManager;
        this.treeManager = treeManager;
        this.wheatManager = wheatManager;
        this.tileSize = tileSize;
        this.scale = scale;
        this.mapData = mapData;
    }

    public void renderObjectLayer(Graphics g, int width, int height) {
        int[][] objectMap = mapData.getObjectMap();
        int scaledTileSize = tileSize * scale;
        int rows = objectMap.length;
        int cols = objectMap[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int objectIndex = objectMap[row][col];
                BufferedImage objectTile = null;

                if (objectIndex == MapDataObject.ROCK_OBJECT) {
                    int rockTileCount = rockManager.getTileCount();
                    int tileIndex = random.nextInt(rockTileCount);
                    objectTile = rockManager.getTileByIndex(tileIndex);
                } else if (objectIndex == MapDataObject.TREE_OBJECT) {
                    int treeTileCount = treeManager.getTileCount();
                    int tileIndex = random.nextInt(treeTileCount);
                    objectTile = treeManager.getTileByIndex(tileIndex);
                } else if (objectIndex == MapDataObject.WHEAT_OBJECT) {
                    int wheatTileCount = wheatManager.getTileCount();
                    int tileIndex = random.nextInt(wheatTileCount);
                    objectTile = wheatManager.getTileByIndex(tileIndex);
                }

                if (objectTile != null) {
                    g.drawImage(objectTile, col * scaledTileSize, row * scaledTileSize, scaledTileSize, scaledTileSize, null);
                }
            }
        }
    }

}