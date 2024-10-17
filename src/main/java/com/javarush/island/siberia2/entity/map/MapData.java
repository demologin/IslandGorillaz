package com.javarush.island.siberia2.entity.map;

import lombok.Getter;

public class MapData {

    private final MapDataTerrain terrainData;
    private final MapDataObject objectData;
    @Getter
    private final int width;
    @Getter
    private final int height;

    public MapData(int width, int height) {
        this.width = width;
        this.height = height;
        terrainData = new MapDataTerrain(width, height);
        objectData = new MapDataObject(width, height, terrainData);
    }

    public int[][] getTerrainMap() {
        return terrainData.getTerrainMap();
    }

    public int[][] getObjectMap() {
        return objectData.getObjectMap();
    }

}