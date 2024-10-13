package com.javarush.island.levchuk.engine;

import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.map.IslandMap;
import com.javarush.island.levchuk.services.EatingService;
import com.javarush.island.levchuk.services.MoveService;
import com.javarush.island.levchuk.services.ReproduceService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;


public class TaskManager {
    public void eatAllInIsland(IslandMap islandMap, EatingService eatingService) {

        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream)
                .map(cell -> (Callable<Void>) () -> {
                    cell.getLock().lock();
                    eatingService.eatAllInCell(cell);
                    cell.getLock().unlock();
                    return null;
                })
                .toList();
    }

    public void moveAllInIsland(IslandMap islandMap, MoveService moveService) {
        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream)
                .map(cell -> (Callable<Void>) () -> {
                    cell.getLock().lock();
                    moveService.moveAllInCall(cell);
                    cell.getLock().unlock();
                    return null;
                })
                .toList();
    }

    public void reproduceAllInIsland(IslandMap islandMap, ReproduceService reproduceService) {
        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream)
                .map(cell -> (Callable<Void>) () -> {
                    cell.getLock().lock();
                    reproduceService.reproduceAllInCall(cell);
                    cell.getLock().unlock();
                    return null;

                })
                .toList();
    }
}
