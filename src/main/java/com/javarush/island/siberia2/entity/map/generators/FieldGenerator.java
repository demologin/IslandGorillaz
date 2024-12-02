package com.javarush.island.siberia2.entity.map.generators;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.map.generators.mapUtil.PlacementChecker;
import java.util.Random;

public class FieldGenerator {

    private final ObjectType[][] objectMap;
    private final Random random;
    private final PlacementChecker placementChecker;

    public FieldGenerator(ObjectType[][] objectMap, PlacementChecker placementChecker, Random random) {
        this.objectMap = objectMap;
        this.placementChecker = placementChecker;
        this.random = random;
    }

    public void generateFields() {
        int fieldCount = Constants.FIELD_COUNT;
        int fieldSize = Constants.FIELD_SIZE;

        for (int i = 0; i < fieldCount; i++) {
            int centerX = random.nextInt(placementChecker.getWidth());
            int centerY = random.nextInt(placementChecker.getHeight());

            for (int y = centerY - fieldSize; y <= centerY + fieldSize; y++) {
                for (int x = centerX - fieldSize; x <= centerX + fieldSize; x++) {
                    if (placementChecker.isValidPlacement(x, y) && placementChecker.isAdjacentToObject(x, y, ObjectType.TREE)) {
                        if (random.nextInt(100) < 80) {
                            objectMap[y][x] = ObjectType.WHEAT;
                        }
                    }
                }
            }
        }
    }

}