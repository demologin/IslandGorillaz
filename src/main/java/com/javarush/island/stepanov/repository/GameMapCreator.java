package com.javarush.island.stepanov.repository;

import com.javarush.island.stepanov.entity.map.GameMap;

public class GameMapCreator {
    private EntityCreator entityCreator;

    public GameMapCreator(EntityCreator entityCreator) {
        this.entityCreator = entityCreator;
    }

    public GameMap createRandomFilledGameMap(int rows, int cols, boolean b) {
        return null;
    }
}
