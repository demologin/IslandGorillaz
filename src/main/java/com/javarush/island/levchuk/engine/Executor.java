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

    private static ConsoleProvider consoleProvider;

    public Executor(ConsoleProvider consoleProvider) {
        this.consoleProvider = consoleProvider;
    }

    public void startGame() {
        consoleProvider.println(ConsoleMessages.INIT_GAME);
        int numberGameDays = getNumberSimulationDays();
        MapInitializer mapInitializer = new MapInitializer(consoleProvider);
        IslandMap islandMap = new IslandMap(mapInitializer);
        mapInitializer.fillMapEntities(islandMap.getIslandMap(), new EntityFactory(), new PrototypesCreator());
        StatisticProvider statisticProvider = new StatisticProvider();
        statisticProvider.printByCell(islandMap, consoleProvider);
        TaskManager taskManager = new TaskManager(new EatingService(),new ReproduceService(),new MoveService());
        ExecutorService executorService = Executors.newWorkStealingPool();
        long start = System.currentTimeMillis();
        try {
            for (int i = 1; i <= numberGameDays; i++) {
                consoleProvider.printfMessage(ConsoleMessages.DAY_NUMBER, String.valueOf(i));
                taskManager.runLifeCycle(islandMap, executorService);
                statisticProvider.printTextStatistic(islandMap, consoleProvider);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        consoleProvider.println(ConsoleMessages.TOTAL_TIME + (end - start) * 1.0 / 1000 + " s");
        statisticProvider.printByCell(islandMap, consoleProvider);
        executorService.shutdown();

    }

    private int getNumberSimulationDays() {
        consoleProvider.printfMessage(ConsoleMessages.ENTER_SIMULATION_DAYS, String.valueOf(Constants.MAX_NUMBER_SIMULATION_DAYS));
        String inputLine = consoleProvider.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= Constants.MAX_NUMBER_SIMULATION_DAYS) {
                    return size;
                }
                throw new IllegalArgumentException(ConsoleMessages.INVALID_INPUT_SIZE);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ConsoleMessages.NOT_A_NUMBER, e);
            }
        }
        throw new IllegalArgumentException(ConsoleMessages.INVALID_INPUT_DATA);
    }
}

