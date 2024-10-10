package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.Game;

public class ViewService implements Runnable{

    private final Game game;

    public ViewService(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        game.getGameMap().updateStatistics();
        game.getView().show();
    }
}
