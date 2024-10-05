package com.javarush.island.levchuk.liveActions;

import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;

public interface Movable {
    public void move(IslandMap islandMap, Cell cell);
}
