package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.entity.map.GameMap;

public class GameMapCreator {
    private final Initialization entityFactory;

    public GameMapCreator(Initialization entityFactory) {
        this.entityFactory = entityFactory;
    }

    public GameMap createRandomFilledGameMap(int rows, int cols, double percentProbably) {
        GameMap gameMap = new GameMap(rows, cols);
        gameMap.getStreamCells()
                .forEach(cell -> entityFactory.fill(cell, percentProbably));
        return gameMap;
    }
}
