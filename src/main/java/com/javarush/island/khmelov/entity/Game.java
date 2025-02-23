package com.javarush.island.khmelov.entity;

import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.api.view.View;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Game {

    private final GameMap gameMap;
    private final Initialization entityFactory;
    private final View view;

}
