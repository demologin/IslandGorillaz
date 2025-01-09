package com.javarush.island.stepanov.view;

import com.javarush.island.stepanov.entity.map.GameMap;

public class ConsoleView implements View {

    private final GameMap gameMap;

    public ConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void show() {

    }

    @Override
    public String showStatistics() {
        return "";
    }

    @Override
    public String showScale() {
        return "";
    }

    @Override
    public String showMap() {
        return "";
    }
}
