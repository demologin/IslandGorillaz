package com.javarush.island.stepanov.view;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.map.GeneralStatistic;
import com.javarush.island.stepanov.entity.map.SortedByValueTreeMap;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.stepanov.config.Setting.ORGANISMS_VIEW_MAP;
import static com.javarush.island.stepanov.constants.Constants.MIN_NUMBER_OF_ORGANISMS;

public class ConsoleView implements View {
    private static final String SEPARATOR_NEW_CELL = "-----------------------------------------------------------------------";
    private static final String SEPARATOR_GENERAL_STATISTIC = "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII";
    private static final String SEPARATOR_NEW_TURN = "=======================================================================";
    private static final String SEPARATOR_AFTER_CELL_INFO = "                ";
    private static final String PERCENT = "% ";
    private static final String DEFIS = "-";
    private static final String SPACE = " ";
    private static final String X_CELL_DESCRIPTION = "Клетка Х=";
    private static final String Y_CELL_DESCRIPTION = " Y=";
    private static final String STEP_DESCRIPTION = "Шаг № ";
    private static final String GENERAL_STATISTIC_DESCRIPTION = "ИТОГОВОЕ КОЛИЧЕСТВО ОРГАНИЗМОВ:";
    private static final String NEXT_LINE = "\n";

    private final GameMap gameMap;
    private Cell[][] cells;

    public ConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void show(int step) {
        showMap(step);
        showGeneralStatistic();
    }

    @Override
    public void showGeneralStatistic() {
        GeneralStatistic generalStatistic = gameMap.getGeneralStatisticsMap();
        Map<String, AtomicInteger> generalStatisticsMap = generalStatistic.getMap();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SEPARATOR_GENERAL_STATISTIC)
                .append(NEXT_LINE)
                .append(GENERAL_STATISTIC_DESCRIPTION)
                .append(NEXT_LINE);
        generalStatisticsMap.forEach((k, v)->{
            String organismName = k;
            Integer value = v.intValue();
            String organismView = ORGANISMS_VIEW_MAP.get(organismName);
            stringBuilder
                    .append(organismView)
                    .append(DEFIS)
                    .append(value)
                    .append(SPACE);
        });
        System.out.println(stringBuilder.toString());
        System.out.println(SEPARATOR_NEW_TURN);
    }

    @Override
    public void showMap(int step) {
        cells = gameMap.getCells();

        System.out.println(STEP_DESCRIPTION + step);
        System.out.println(SEPARATOR_NEW_TURN);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder
                        .append(X_CELL_DESCRIPTION)
                        .append(i)
                        .append(Y_CELL_DESCRIPTION)
                        .append(j + SEPARATOR_AFTER_CELL_INFO);
                SortedByValueTreeMap<String, Integer> statisticMap = cells[i][j].getPopulationStatistics();
                for (Map.Entry<String, Integer> entry : statisticMap.entrySet()) {
                    String organismName = entry.getKey();
                    String organismView = ORGANISMS_VIEW_MAP.get(organismName);
                    Integer organismPopulation = entry.getValue();
                    stringBuilder
                            .append(organismView)
                            .append(DEFIS)
                            .append(organismPopulation)
                            .append(PERCENT);
                }
                stringBuilder.append(NEXT_LINE);
                stringBuilder.append(SEPARATOR_NEW_CELL);
                System.out.println(stringBuilder.toString());
            }
        }
    }
}
