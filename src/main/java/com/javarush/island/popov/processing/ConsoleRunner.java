package com.javarush.island.popov.processing;
import com.javarush.island.popov.creators.IslandMapCreator;
import com.javarush.island.popov.creators.StatisticsCreator;
import com.javarush.island.popov.creators.TasksCreator;
import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.repo.Constants;
import com.javarush.island.popov.view.ConsoleInputOutput;
import com.javarush.island.popov.view.ConsoleView;
import java.util.List;


public class ConsoleRunner {
    public static void main(String[] args) {
        ConsoleInputOutput consoleInputOutput = new ConsoleInputOutput();
        consoleInputOutput.write(Constants.HORIZONTAL_RANGE);
        Constants.COLS = consoleInputOutput.read();
        consoleInputOutput.write(Constants.VERTICAL_RANGE);
        Constants.ROWS = consoleInputOutput.read();
        IslandMapCreator islandMapCreator = new IslandMapCreator();
        IslandMap map = islandMapCreator.createMap(Constants.ROWS, Constants.COLS);

        consoleInputOutput.write(Constants.LIFE_DAY_NUMBERS);
        Constants.DAYS_NUMBER = consoleInputOutput.read();
        consoleInputOutput.write(Constants.CREATE_MAP_AND_FILL);
        StatisticsCreator statistics = new StatisticsCreator();
        statistics.getStatisticByUnits(map);
        ConsoleView consoleView = new ConsoleView();
        consoleView.showStaticticByUnits(statistics, map);

        List<Runnable> tasks = TasksCreator.createTasks(map);
        IslandLifeProcessor islandLifeProcessor = new IslandLifeProcessor(map, tasks);
        islandLifeProcessor.start();
    }
}
