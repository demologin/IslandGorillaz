package com.javarush.island.khmelov;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.Game;
import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.repository.EntityCreator;
import com.javarush.island.khmelov.repository.GameMapCreator;
import com.javarush.island.khmelov.services.*;
import com.javarush.island.khmelov.view.javafx.JavaFxView;

import java.util.List;

public class JavaFxRunner {

    public static void main(String[] args) {
        Initialization entityFactory = new EntityCreator();
        GameMapCreator gameMapCreator = new GameMapCreator(entityFactory);
        int rows = Setting.get().life.getRows();
        int cols = Setting.get().life.getCols();
        GameMap gameMap = gameMapCreator.createRandomFilledGameMap(rows, cols, 1);
        Game game = new Game(gameMap, entityFactory, null);
        List<Runnable> services = List.of(
                new EatingService(game),
                new MovingService(game),
                new ReproducingService(game),
                new RandomFillService(game),
                new ViewService(game)
        );
        GameServiceProcessor gameWorkerService = new GameServiceProcessor(game, services);
        JavaFxView.launchFxWindow(gameWorkerService);
    }
}