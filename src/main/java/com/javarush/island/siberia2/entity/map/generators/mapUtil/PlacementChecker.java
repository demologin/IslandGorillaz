package com.javarush.island.siberia2.entity.map.generators.mapUtil;

import com.javarush.island.siberia2.entity.map.generators.ObjectType;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import com.javarush.island.siberia2.entity.map.MapDataTerrain;
import lombok.Getter;

public class PlacementChecker {

    @Getter
    private final int width;
    @Getter
    private final int height;
    private final ObjectType[][] objectMap;
    private final MapDataTerrain terrainData;

    public PlacementChecker(ObjectType[][] objectMap, MapDataTerrain terrainData, int width, int height) {
        this.objectMap = objectMap;
        this.terrainData = terrainData;
        this.width = width;
        this.height = height;
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isValidPlacement(int x, int y) {
        if (!isInBounds(x, y)) {
            return false;
        }
        TerrainType tile = terrainData.getTile(x, y);
        ObjectType object = objectMap[y][x];
        return tile != TerrainType.WATER
                && tile != TerrainType.PATH
                && tile != TerrainType.BRIDGE
                && object == ObjectType.NO_OBJECT;
    }

    public boolean isAdjacentToObject(int x, int y, ObjectType objectType) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;
                if (isInBounds(nx, ny) && objectMap[ny][nx] == objectType) {
                    return false;
                }
            }
        }
        return true;
    }

}