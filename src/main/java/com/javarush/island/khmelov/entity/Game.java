package com.javarush.island.khmelov.entity;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.api.view.View;
import com.javarush.island.khmelov.entity.map.GameMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private final GameMap gameMap;
    private final Initialization entityFactory;
    private View view;
    private boolean isFinished = false;

    public Game(GameMap gameMap, Initialization entityFactory, View view) {
        this.gameMap = gameMap;
        this.entityFactory = entityFactory;
        this.view = view;
    }
}
