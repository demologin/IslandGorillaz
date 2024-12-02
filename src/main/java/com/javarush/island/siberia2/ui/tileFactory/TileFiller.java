package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.entity.map.MapData;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TileFiller {
    private final TileManager tileManager;
    private final int tileSize;
    private final int scale;
    private final MapData mapData;

    public TileFiller(TileManager tileManager, int tileSize, int scale, MapData mapData) {
        this.tileManager = tileManager;
        this.tileSize = tileSize;
        this.scale = scale;
        this.mapData = mapData;
    }

    public void renderWorld(Graphics g, int width, int height) {
        TerrainType[][] terrainMap = mapData.getTerrainMap();
        int scaledTileSize = tileSize * scale;
        int cols = Math.min(terrainMap[0].length, width / scaledTileSize);
        int rows = Math.min(terrainMap.length, height / scaledTileSize);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TerrainType tileType = terrainMap[row][col];
                BufferedImage tile = tileManager.getTileByTerrainType(tileType);

                if (tile != null) {
                    g.drawImage(tile,
                            col * scaledTileSize,
                            row * scaledTileSize,
                            scaledTileSize,
                            scaledTileSize,
                            null);
                }
            }
        }
    }

}