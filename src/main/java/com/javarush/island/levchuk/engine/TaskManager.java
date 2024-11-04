package com.javarush.island.levchuk.engine;

import com.javarush.island.levchuk.map.IslandMap;
import com.javarush.island.levchuk.services.EatingService;
import com.javarush.island.levchuk.services.MoveService;
import com.javarush.island.levchuk.services.ReproduceService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;


public class TaskManager {
    private static EatingService eatingService;
    private static MoveService moveService;
    private static ReproduceService reproduceService;

    public TaskManager(EatingService eatingService, ReproduceService reproduceService, MoveService moveService) {
        this.reproduceService = reproduceService;
        this.moveService = moveService;
        this.eatingService = eatingService;
    }

    public void runLifeCycle(IslandMap islandMap, ExecutorService executorService) throws InterruptedException {
        eatAllInIsland(islandMap, executorService);
        moveAllInIsland(islandMap, executorService);
        reproduceAllInIsland(islandMap, executorService);
    }

    private void eatAllInIsland(IslandMap islandMap, ExecutorService executorService) throws InterruptedException {

        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream)
                .map(cell -> (Callable<Void>) () -> {
                    eatingService.eatAllInCell(cell);
                    return null;
                }).toList();
        executorService.invokeAll(tasks);
    }

    private void moveAllInIsland(IslandMap islandMap, ExecutorService executorService) throws InterruptedException {
        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream).map(cell -> (Callable<Void>) () -> {
                    moveService.moveAllInCall(cell);
                    return null;
                }).toList();
        executorService.invokeAll(tasks);
    }

    private void reproduceAllInIsland(IslandMap islandMap, ExecutorService executorService) throws InterruptedException {
        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream)
                .map(cell -> (Callable<Void>) () -> {
                    reproduceService.reproduceAllInCall(cell);
                    return null;
                }).toList();
        executorService.invokeAll(tasks);
    }
}
