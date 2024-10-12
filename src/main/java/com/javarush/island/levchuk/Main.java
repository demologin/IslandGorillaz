package com.javarush.island.levchuk;

import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;
import com.javarush.island.levchuk.utils.MapInitializer;

public class Main {
    public static void main(String[] args) {
        IslandMap islandMap = new IslandMap();
        MapInitializer mapInitializer = new MapInitializer();
        islandMap.initMap(mapInitializer);

    }
}
