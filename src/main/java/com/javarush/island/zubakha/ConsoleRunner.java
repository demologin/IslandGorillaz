package com.javarush.island.zubakha;

import com.javarush.island.zubakha.entity.map.GameMap;
import com.javarush.island.zubakha.entity.map.IslandSimulation;

public class ConsoleRunner {

    public static void main(String[] args) {
        GameMap.getInstance().createMap();
        IslandSimulation.getInstance().createIslandModel();

    }
}
