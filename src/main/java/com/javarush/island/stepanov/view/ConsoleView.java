package com.javarush.island.stepanov.view;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.services.StatisticService;

import java.util.Map;
import java.util.SortedMap;

public class ConsoleView implements View {

    private final GameMap gameMap;
    private Cell[][] cells ;
    StatisticService statisticService = new StatisticService();


    public ConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;

    }

    @Override
    public void show(int step) {
        showMap(step);
        showStatistics();
    }

    @Override
    public String showStatistics() {
        return "";
    }

    @Override
    public String showScale() {
        return "";
    }

    @Override
    public void showMap(int step) {
        System.out.println("Step: " + step);
        cells = gameMap.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                statisticService.calcStatisticsOnCell(cells[i][j]);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Клетка Х=").append(i).append(" Y=").append(j+"______________").append(" ");
                SortedMap<Integer, String> statisticMap = cells[i][j].getPopulationStatistics();
                for (Map.Entry<Integer, String> entry : statisticMap.entrySet()) {
                    stringBuilder.append(entry.getValue()).append("-").append(entry.getKey()).append("% ");
                }
                stringBuilder.append("\n");
                stringBuilder.append("-----------------------------------------------------------------------");
                System.out.println(stringBuilder.toString());
            }
        }
        System.out.println("=======================================================================");
    }
}
