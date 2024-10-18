package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.Game;

import java.util.Arrays;

public class MovingService extends AbstractService {

    public MovingService(Game game) {
        super(game);
    }

    @Override
    public void run() {
        Arrays.stream(game.getGameMap().getCells()) //row
                .flatMap(Arrays::stream) //cell
                .forEach(cell -> processOneCell(cell, o -> o.move(cell)));
    }
}
