package com.javarush.island.siberia2.ui.tileFactory;

import com.javarush.island.siberia2.entity.map.generators.ObjectType;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import lombok.Getter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class TileManager {
    private final Map<TerrainType, BufferedImage> terrainImages = new EnumMap<>(TerrainType.class);
    private final Map<ObjectType, BufferedImage> objectImages = new EnumMap<>(ObjectType.class);
    private final BufferedImage asset;
    private final int tileSize;
    @Getter
    private final int totalTiles;

    public TileManager(String resourcePath, int tileSize) {
        this.tileSize = tileSize;
        try {
            this.asset = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(resourcePath)));
            int assetWidth = asset.getWidth();
            int assetHeight = asset.getHeight();

            this.totalTiles = (assetWidth / tileSize) * (assetHeight / tileSize);

            for (TerrainType type : TerrainType.values()) {
                int index = type.ordinal();
                int cols = assetWidth / tileSize;
                int x = (index % cols) * tileSize;
                int y = (index / cols) * tileSize;
                if (x + tileSize <= assetWidth && y + tileSize <= assetHeight) {
                    terrainImages.put(type, asset.getSubimage(x, y, tileSize, tileSize));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getTileByIndex(int index) {
        int cols = asset.getWidth() / tileSize;
        int x = (index % cols) * tileSize;
        int y = (index / cols) * tileSize;
        return asset.getSubimage(x, y, tileSize, tileSize);
    }

    public BufferedImage getTileByTerrainType(TerrainType terrainType) {
        return terrainImages.get(terrainType);
    }

    public BufferedImage getTileByObjectType(ObjectType objectType) {
        return objectImages.get(objectType);
    }

}