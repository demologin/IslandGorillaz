package com.javarush.island.levchuk.map;

import com.javarush.island.levchuk.utils.MapInitializer;
import lombok.Getter;

@Getter
public class IslandMap {
    private final Cell[][] islandMap;

    public IslandMap(MapInitializer mapInitializer) {
        islandMap = mapInitializer.getMapArray();
    }


}