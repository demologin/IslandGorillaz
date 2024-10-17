package com.javarush.island.siberia2.entity.map;

import com.javarush.island.siberia2.entity.map.generators.ObjectType;
import com.javarush.island.siberia2.entity.map.generators.mapUtil.PlacementChecker;
import com.javarush.island.siberia2.entity.map.generators.RockPlacer;
import com.javarush.island.siberia2.entity.map.generators.FieldGenerator;
import com.javarush.island.siberia2.entity.map.generators.ForestGenerator;
import lombok.Getter;
import java.util.Random;

public class MapDataObject {
    @Getter
    private final ObjectType[][] objectMap;
    private final int width;
    private final int height;
    private final RockPlacer rockPlacer;
    private final FieldGenerator fieldGenerator;
    private final ForestGenerator forestGenerator;

    public MapDataObject(int width, int height, MapDataTerrain terrainData) {
        this.width = width;
        this.height = height;
        this.objectMap = new ObjectType[height][width];

        PlacementChecker placementChecker = new PlacementChecker(objectMap, terrainData, width, height);
        Random random = new Random();
        this.rockPlacer = new RockPlacer(objectMap, placementChecker, random);
        this.fieldGenerator = new FieldGenerator(objectMap, placementChecker, random);
        this.forestGenerator = new ForestGenerator(objectMap, placementChecker, random);

        generateObjectMap();
    }

    private void generateObjectMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                objectMap[y][x] = ObjectType.NO_OBJECT;
            }
        }
        rockPlacer.placeRocks();
        fieldGenerator.generateFields();
        forestGenerator.generateForests();
    }

}