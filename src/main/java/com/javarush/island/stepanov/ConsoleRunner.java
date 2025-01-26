package com.javarush.island.stepanov;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.Game;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.repository.EntityCreator;
import com.javarush.island.stepanov.repository.GameMapCreator;
import com.javarush.island.stepanov.services.workers.GameWorker;
import com.javarush.island.stepanov.view.ConsoleView;
import com.javarush.island.stepanov.view.View;

public class ConsoleRunner {
    public static void main(String[] args) {
        EntityCreator entityCreator = new EntityCreator();
        GameMapCreator gameMapCreator = new GameMapCreator(entityCreator);
        int rows = Setting.get().getRows();
        int cols = Setting.get().getCols();
        GameMap gameMap = gameMapCreator.createRandomFilledGameMap(rows, cols, false);
        View view = new ConsoleView(gameMap);
        Game game = new Game(gameMap, entityCreator, view);
        GameWorker gameWorker = new GameWorker(game);
        gameWorker.start();
    }
}
