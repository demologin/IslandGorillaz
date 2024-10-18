package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.entity.map.generators.ObjectType;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import lombok.Getter;

public class MapData {
    @Getter
    private final int width;
    @Getter
    private final int height;
    private final MapDataTerrain terrainData;
    private final MapDataObject objectData;

    public MapData(int width, int height) {
        this.width = width;
        this.height = height;
        terrainData = new MapDataTerrain(width, height);
        objectData = new MapDataObject(width, height, terrainData);
    }

    public TerrainType[][] getTerrainMap() {
        return terrainData.getTerrainMap();
    }

    public ObjectType[][] getObjectMap() {
        return objectData.getObjectMap();
    }

}