package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.entity.map.generators.BaseTerrainGenerator;
import com.javarush.island.siberia2.entity.map.generators.PathGenerator;
import com.javarush.island.siberia2.entity.map.generators.TerrainType;
import com.javarush.island.siberia2.entity.map.generators.WaterGenerator;
import lombok.Getter;

public class MapDataTerrain {

    @Getter
    private final TerrainType[][] terrainMap;
    private final int width;
    private final int height;

    private final BaseTerrainGenerator baseTerrainGenerator = new BaseTerrainGenerator();
    private final WaterGenerator waterGenerator = new WaterGenerator();
    private final PathGenerator pathGenerator = new PathGenerator();

    public MapDataTerrain(int width, int height) {
        this.width = width;
        this.height = height;
        terrainMap = new TerrainType[height][width];
        generateTerrainMap();
    }

    private void generateTerrainMap() {
        baseTerrainGenerator.generateBaseTerrain(terrainMap, width, height);
        waterGenerator.generateWater(terrainMap, width, height);
        pathGenerator.generatePaths(terrainMap, width, height);
    }

    public TerrainType getTile(int x, int y) {
        return terrainMap[y][x];
    }

}