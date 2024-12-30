package com.javarush.island.nikitin.presentation.entity;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//todo рефакторинг!
public class PrintCell {
    public static final int LIMIT_SHOW_UNIT = 6;

    public void printCell(EcoSystem ecoSystem) {
        Location[][] data = ecoSystem.getLocationsForView();
        StringBuilder output = new StringBuilder();

        //output.append(printMiddleBorder(data[0].length, 25));
        for (Location[] datum : data) {
            output.append("║ ");

            for (Location location : datum) {
                var dataMap = location.getPopulations();
                output.append(formatValues(dataMap));
                output.append("║ ");
            }

            output.append("\n"); // Закрываем строку таблицы
            //output.append(printMiddleBorder(data[0].length, 25));
        }
        //output.append(printBottomBorder(data.length, 28));
        System.out.print(output.toString());
    }

    private static String formatValues(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> map) {
        StringBuilder values = new StringBuilder();

        List<Set<Biota>> sets = List.copyOf(map.values());

        for (int i = 0; i < LIMIT_SHOW_UNIT; i++) {
            int result = 0;

            if (i < sets.size()) {
                result = sets.get(i).size();
            }
            if (result == 0) {
                values.append("  ");
                values.append(String.format("%-4s", ""));
            } else {
                String icon = sets.get(i).stream().findFirst().get().getProperty().getIcon();
                values.append(icon);
                values.append(String.format("%-4d", result));
            }
        }
        return values.toString();
    }
    /*
    //todo Метод для печати верхней границы таблицы
    public static String printTopBorder(int columns, int cellWidth) {
        StringBuilder border = new StringBuilder("╔");
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < cellWidth; j++) {
                border.append("═");
            }
            border.append("╦");
        }
        border.deleteCharAt(border.length() - 1);
        border.append("╗\n");
        return border.toString();
    }

    //todo Метод для печати средней границы таблицы
    private static String printMiddleBorder(int columns, int cellWidth) {
        StringBuilder border = new StringBuilder("╠");
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < cellWidth; j++) {
                border.append("═");
            }
            border.append("╬");
        }
        border.deleteCharAt(border.length() - 1);
        border.append("╣\n");
        return border.toString();
    }

    //todo Метод для печати нижней границы таблицы
    private static String printBottomBorder(int columns, int cellWidth) {
        StringBuilder border = new StringBuilder("╚");
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < cellWidth; j++) {
                border.append("═");
            }
            border.append("╩");
        }
        border.deleteCharAt(border.length() - 1);
        border.append("╝\n");
        return border.toString();
    }
    */
}
