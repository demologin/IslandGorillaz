package com.javarush.island.siberia2.entity.map.generators;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.map.generators.mapUtil.PlacementChecker;
import java.util.Random;

public class ForestGenerator {

    private final ObjectType[][] objectMap;
    private final Random random;
    private final PlacementChecker placementChecker;

    public ForestGenerator(ObjectType[][] objectMap, PlacementChecker placementChecker, Random random) {
        this.objectMap = objectMap;
        this.placementChecker = placementChecker;
        this.random = random;
    }

    public void generateForests() {
        int forestCount = Constants.FOREST_COUNT;
        int forestSize = Constants.FOREST_SIZE;

        for (int i = 0; i < forestCount; i++) {
            int centerX = random.nextInt(placementChecker.getWidth());
            int centerY = random.nextInt(placementChecker.getHeight());

            for (int y = centerY - forestSize; y <= centerY + forestSize; y++) {
                for (int x = centerX - forestSize; x <= centerX + forestSize; x++) {
                    if (placementChecker.isValidPlacement(x, y) && placementChecker.isAdjacentToObject(x, y, ObjectType.WHEAT)) {
                        if (random.nextInt(100) < 70) {
                            objectMap[y][x] = ObjectType.TREE;
                        }
                    }
                }
            }
        }
    }

}