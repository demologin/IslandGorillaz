package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.entity.map.MapData;
import com.javarush.island.siberia2.entity.map.generators.ObjectType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class ObjectLayer {
    private final Map<ObjectType, TileManager> objectTileManagers = new EnumMap<>(ObjectType.class);
    private final int tileSize;
    private final int scale;
    private final MapData mapData;
    private final Random random = new Random();

    public ObjectLayer(TileManager rockManager,
                       TileManager treeManager,
                       TileManager wheatManager,
                       int tileSize,
                       int scale,
                       MapData mapData) {
        this.tileSize = tileSize;
        this.scale = scale;
        this.mapData = mapData;

        objectTileManagers.put(ObjectType.ROCK, rockManager);
        objectTileManagers.put(ObjectType.TREE, treeManager);
        objectTileManagers.put(ObjectType.WHEAT, wheatManager);
    }

    public void renderObjectLayer(Graphics g, int width, int height) {
        ObjectType[][] objectMap = mapData.getObjectMap();
        int scaledTileSize = tileSize * scale;
        int rows = objectMap.length;
        int cols = objectMap[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                ObjectType objectType = objectMap[row][col];
                BufferedImage objectTile = null;

                TileManager tileManager = objectTileManagers.get(objectType);
                if (tileManager != null) {
                    if (objectType == ObjectType.TREE || objectType == ObjectType.WHEAT) {
                        int tileIndex = random.nextInt(tileManager.getTotalTiles());
                        objectTile = tileManager.getTileByIndex(tileIndex);
                    } else {
                        objectTile = tileManager.getTileByObjectType(objectType);
                    }
                }

                if (objectTile != null) {
                    g.drawImage(objectTile,
                            col * scaledTileSize,
                            row * scaledTileSize, scaledTileSize, scaledTileSize,
                            null);
                }
            }
        }
    }

}