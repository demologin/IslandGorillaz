package com.javarush.island.ivanov.api.engine;

import com.javarush.island.ivanov.api.services.EatingService;
import com.javarush.island.ivanov.api.services.MoveService;
import com.javarush.island.ivanov.api.services.ReproduceService;
import com.javarush.island.ivanov.constants.ConsoleMessages;
import com.javarush.island.ivanov.constants.Constants;
import com.javarush.island.ivanov.entity.map.GameMap;
import com.javarush.island.ivanov.utils.MapLoader;
import com.javarush.island.ivanov.utils.OrganismsFactory;
import com.javarush.island.ivanov.utils.PrototypesCreator;
import com.javarush.island.ivanov.view.ConsoleProvider;
import com.javarush.island.ivanov.view.StatisticProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

    ConsoleProvider console = new ConsoleProvider();
    MapLoader mapInitializer = new MapLoader(console);

    OrganismsFactory entityFactory = new OrganismsFactory();
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
        GameMap islandMap = new GameMap(mapInitializer);
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
