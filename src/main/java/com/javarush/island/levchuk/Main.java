package com.javarush.island.levchuk;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;
import com.javarush.island.levchuk.services.EatingService;
import com.javarush.island.levchuk.services.MoveService;
import com.javarush.island.levchuk.services.ReproduceService;
import com.javarush.island.levchuk.utils.MapInitializer;

public class Main {
    public static void main(String[] args) {
        IslandMap islandMap = new IslandMap(10,10);
        islandMap.initMap(new MapInitializer());
        Cell cell = islandMap.getIslandMap()[1][1];
        MoveService moveService = new MoveService();
        moveService.moveAllInCall(cell);
        EatingService eatingService = new EatingService();
        eatingService.eatAllInCell(cell);
        ReproduceService reproduceService = new ReproduceService();
        reproduceService.reproduceAllInCall(cell);
    }
}
