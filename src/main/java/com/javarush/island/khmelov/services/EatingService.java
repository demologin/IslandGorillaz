package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.Game;

public class EatingService extends AbstractService {

    public EatingService(Game game) {
        super(game);
    }

    @Override
    public void run() {
        game.getGameMap().getStreamCells()
                .forEach(cell -> processOneCell(cell, o -> o.eat(cell)));
    }
}
