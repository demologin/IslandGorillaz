package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.entity.Game;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.util.Rnd;

public class RandomFillService extends AbstractService {

    public static final double PERCENT_PROBABLY = 1;
    private final int ROWS;
    private final int COLS;
    private final Initialization entityFactory;

    public RandomFillService(Game game) {
        super(game);
        ROWS = game.getGameMap().getRows();
        COLS = game.getGameMap().getCols();
        entityFactory = game.getEntityFactory();
    }

    @Override
    public void run() {
        int row = Rnd.random(0, ROWS);
        int col = Rnd.random(0, COLS);
        Cell cell = game.getGameMap().getCells()[row][col];
        safeFill(cell);
    }

    private void safeFill(Cell cell) {
        cell.getLock().lock();
        try {
            entityFactory.fill(cell, PERCENT_PROBABLY);
        } finally {
            cell.getLock().unlock();
        }
    }
}
