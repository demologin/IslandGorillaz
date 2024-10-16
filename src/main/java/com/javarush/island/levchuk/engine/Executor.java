package com.javarush.island.levchuk.engine;

import com.javarush.island.levchuk.constants.ConsoleMessages;
import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.map.IslandMap;
import com.javarush.island.levchuk.services.EatingService;
import com.javarush.island.levchuk.services.MoveService;
import com.javarush.island.levchuk.services.ReproduceService;
import com.javarush.island.levchuk.utils.EntityFactory;
import com.javarush.island.levchuk.utils.MapInitializer;
import com.javarush.island.levchuk.utils.PrototypesCreator;
import com.javarush.island.levchuk.view.ConsoleProvider;
import com.javarush.island.levchuk.view.StatisticProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    ConsoleProvider console = new ConsoleProvider();
    MapInitializer mapInitializer = new MapInitializer(console);

    EntityFactory entityFactory = new EntityFactory();
    PrototypesCreator prototypesCreator = new PrototypesCreator();
    TaskManager taskManager = new TaskManager();
    EatingService eatingService = new EatingService();
    MoveService moveService = new MoveService();
    ReproduceService reproduceService = new ReproduceService();
    ExecutorService executorService = Executors.newWorkStealingPool();
    StatisticProvider statisticProvider = new StatisticProvider();

    public void startGame() {
        console.println(ConsoleMessages.INIT_GAME);
        int numberGameDays = getNumberSimulationDays();
        IslandMap islandMap = new IslandMap(mapInitializer);
        mapInitializer.fillMapEntities(islandMap.getIslandMap(), entityFactory, prototypesCreator);
        statisticProvider.printByCell(islandMap, console);
        long start = System.currentTimeMillis();
        try {
            for (int i = 1; i <= numberGameDays; i++) {
                console.printfMessage(ConsoleMessages.DAY_NUMBER, String.valueOf(i));
                taskManager.reproduceAllInIsland(islandMap, reproduceService, executorService);
                taskManager.moveAllInIsland(islandMap, moveService, executorService);
                taskManager.eatAllInIsland(islandMap, eatingService, executorService);
                statisticProvider.printTextStatistic(islandMap, console);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        console.println(ConsoleMessages.TOTAL_TIME + (end - start) * 1.0 / 1000 + " s");
        statisticProvider.printByCell(islandMap, console);
        executorService.shutdown();

    }

    private int getNumberSimulationDays() {
        console.printfMessage(ConsoleMessages.ENTER_SIMULATION_DAYS, String.valueOf(Constants.MAX_NUMBER_SIMULATION_DAYS));
        String inputLine = console.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= Constants.MAX_NUMBER_SIMULATION_DAYS) {
                    return size;
                }
                throw new IllegalArgumentException(ConsoleMessages.INVALID_INPUT_SIZE);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ConsoleMessages.NOT_A_NUMBER);
            }
        }
        throw new IllegalArgumentException(ConsoleMessages.INVALID_INPUT_DATA);
    }
}

