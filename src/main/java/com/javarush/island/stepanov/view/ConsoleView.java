package com.javarush.island.stepanov.view;

import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.map.GameMap;
import com.javarush.island.stepanov.entity.map.SortedByValueTreeMap;
import com.javarush.island.stepanov.services.StatisticService;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.javarush.island.stepanov.config.Setting.ORGANISMS_VIEW_MAP;

public class ConsoleView implements View {

    private final GameMap gameMap;
    private Cell[][] cells ;



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
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Клетка Х=").append(i).append(" Y=").append(j+"______________").append(" ");
                SortedByValueTreeMap<String, Integer> statisticMap = cells[i][j].getPopulationStatistics();
                for (Map.Entry<String, Integer> entry : statisticMap.entrySet()) {
                    String organismName = entry.getKey();
                    String organismView = ORGANISMS_VIEW_MAP.get(organismName);
                    Integer organismPopulation = entry.getValue();
                    stringBuilder.append(organismView).append("-").append(organismPopulation).append("% ");
                }
                stringBuilder.append("\n");
                stringBuilder.append("-----------------------------------------------------------------------");
                System.out.println(stringBuilder.toString());
            }
        }
        System.out.println("=======================================================================");
    }
}
