package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.Game;

public class ReproducingService extends AbstractService {

    public ReproducingService(Game game) {
        super(game);
    }

    @Override
    public void run() {
        game.getGameMap().getStreamCells()
                .forEach(cell -> processOneCell(cell, o -> o.spawn(cell)));
    }
}
