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
import com.javarush.island.levchuk.view.StatisticPrinter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Executor {
    ConsoleProvider console = new ConsoleProvider();
    IslandMap islandMap = new IslandMap(10, 10);
    EntityFactory entityFactory = new EntityFactory();
    MapInitializer mapInitializer = new MapInitializer();
    PrototypesCreator prototypesCreator = new PrototypesCreator();
    TaskManager taskManager = new TaskManager();
    EatingService eatingService = new EatingService();
    MoveService moveService = new MoveService();
    ReproduceService reproduceService = new ReproduceService();
    ExecutorService executorService = Executors.newWorkStealingPool();
    StatisticPrinter statisticPrinter = new StatisticPrinter();

    public void startGame() {
        console.println(ConsoleMessages.INIT_GAME);
        int numberGameDays = getNumberSimulationDays();
        mapInitializer.fillMapEntities(islandMap.getIslandMap(), entityFactory, prototypesCreator);
        statisticPrinter.printIconsMap(islandMap, console);
        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < numberGameDays; i++) {
                console.println("************ day " + i + " ************");
                taskManager.reproduceAllInIsland(islandMap, reproduceService, executorService);
                taskManager.moveAllInIsland(islandMap, moveService, executorService);
                taskManager.eatAllInIsland(islandMap, eatingService, executorService);
                statisticPrinter.printTextStatistic(islandMap, console);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        console.println("Total time: " + (end - start) * 1.0 / 1000 + " s");
        statisticPrinter.printIconsMap(islandMap, console);
        executorService.shutdown();

    }

    private int getNumberSimulationDays() {
        console.print("Enter number simulation days (1:" + Constants.MAX_NUMBER_SIMULATION_DAYS + "): ");
        String inputLine = console.read();
        if (inputLine != null) {
            try {
                int size = Integer.parseInt(inputLine);
                if (size > 0 && size <= Constants.MAX_NUMBER_SIMULATION_DAYS) {
                    return size;
                }
                throw new IllegalArgumentException("Invalid input size. Check input data. ");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input is not a number. Check input data. ");
            }
        }
        throw new IllegalArgumentException("Invalid input data. Check input data. ");
    }
}

