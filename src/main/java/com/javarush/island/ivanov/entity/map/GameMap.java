package com.javarush.island.ivanov.entity.map;


import com.javarush.island.ivanov.utils.MapLoader;
import lombok.Getter;

@Getter
public class GameMap {

    private final Cell[][] islandMap;

    public GameMap(MapLoader mapLoader) {
        islandMap = mapLoader.getMapArray();
    }
}
