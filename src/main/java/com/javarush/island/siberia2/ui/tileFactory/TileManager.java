package com.javarush.island.siberia2.ui.tileFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class TileManager {
    private BufferedImage asset;
    private final int tileSize;
    private int tilesPerRow;

    public TileManager(String resourcePath, int tileSize) {
        this.tileSize = tileSize;
        try {
            asset = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(resourcePath)));
            tilesPerRow = asset.getWidth() / tileSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTileByIndex(int index) {
        int x = (index % tilesPerRow) * tileSize;
        int y = (index / tilesPerRow) * tileSize;

        return asset.getSubimage(x, y, tileSize, tileSize);
    }

    public int getTileCount() {
        int tilesPerColumn = asset.getHeight() / tileSize;
        return tilesPerRow * tilesPerColumn;
    }

}