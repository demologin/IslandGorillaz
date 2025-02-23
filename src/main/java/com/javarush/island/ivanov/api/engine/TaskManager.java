package com.javarush.island.ivanov.api.engine;

import com.javarush.island.ivanov.api.services.EatingService;
import com.javarush.island.ivanov.api.services.MoveService;
import com.javarush.island.ivanov.api.services.ReproduceService;
import com.javarush.island.ivanov.entity.map.GameMap;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class TaskManager {

    public void eatAllInIsland(GameMap islandMap, EatingService eatingService, ExecutorService executorService) throws InterruptedException {

        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream).map(cell -> (Callable<Void>) () -> {
                    eatingService.eatAllInCell(cell);
                    return null;
                }).toList();
        executorService.invokeAll(tasks);
    }

    public void moveAllInIsland(GameMap islandMap, MoveService moveService, ExecutorService executorService) throws InterruptedException {
        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream).map(cell -> (Callable<Void>) () -> {
                    moveService.moveAllInCall(cell);
                    return null;
                }).toList();
        executorService.invokeAll(tasks);
    }

    public void reproduceAllInIsland(GameMap islandMap, ReproduceService reproduceService, ExecutorService executorService) throws InterruptedException {
        List<Callable<Void>> tasks = Arrays.stream(islandMap.getIslandMap())
                .flatMap(Arrays::stream).map(cell -> (Callable<Void>) () -> {
                    reproduceService.reproduceAllInCall(cell);
                    return null;
                }).toList();
        executorService.invokeAll(tasks);
    }
}
