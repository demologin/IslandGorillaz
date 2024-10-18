package com.javarush.island.siberia2.entity.map.generators;

import com.javarush.island.siberia2.entity.map.generators.mapUtil.PlacementChecker;
import java.util.Random;

public class RockPlacer {

    private final ObjectType[][] objectMap;
    private final Random random;
    private final PlacementChecker placementChecker;

    public RockPlacer(ObjectType[][] objectMap, PlacementChecker placementChecker, Random random) {
        this.objectMap = objectMap;
        this.placementChecker = placementChecker;
        this.random = random;
    }

    public void placeRocks() {
        int rockCount = (placementChecker.getWidth() * placementChecker.getHeight()) / 50;
        for (int i = 0; i < rockCount; i++) {
            int x = random.nextInt(placementChecker.getWidth());
            int y = random.nextInt(placementChecker.getHeight());
            if (placementChecker.isValidPlacement(x, y)) {
                objectMap[y][x] = ObjectType.ROCK;
            }
        }
    }

}