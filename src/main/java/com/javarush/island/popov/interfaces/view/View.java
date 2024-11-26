package com.javarush.island.popov.interfaces.view;

import com.javarush.island.popov.creators.StatisticsCreator;
import com.javarush.island.popov.map.IslandMap;

public interface View {
    void showStaticticByUnits(StatisticsCreator statistics, IslandMap map);
    void showStaticticByCells(IslandMap map);

}
