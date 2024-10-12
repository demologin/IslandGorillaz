package com.javarush.island.siberia2.ui.tileFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TileFiller {
    private TileManager tileManager;
    private int tileSize;
    private int scale;
    private Random random;

    public TileFiller(TileManager tileManager, int tileSize, int scale) {
        this.tileManager = tileManager;
        this.tileSize = tileSize;
        this.scale = scale;
        random = new Random();
    }

    public void fillTiles(Graphics g, int islandWidth, int islandHeight, int width, int height) {
        int scaledTileSize = tileSize * scale;

        int cols = Math.min(islandWidth, width / scaledTileSize);
        int rows = Math.min(islandHeight, height / scaledTileSize);
        int tileCount = tileManager.getTileCount();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int randomTileIndex = random.nextInt(tileCount);
                BufferedImage tile = tileManager.getTileByIndex(randomTileIndex);

                g.drawImage(tile, col * scaledTileSize, row * scaledTileSize, scaledTileSize, scaledTileSize, null);
            }
        }
    }

}