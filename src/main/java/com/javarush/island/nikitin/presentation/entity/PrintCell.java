package com.javarush.island.nikitin.presentation.entity;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//todo рефакторинг!
public class PrintCell {
    public static final int LIMIT_SHOW_UNIT = 6;

    public void printCell(EcoSystem ecoSystem) {
        Location[][] data = ecoSystem.getLocationsForView();
        StringBuilder output = new StringBuilder();
        for (Location[] datum : data) {
            output.append("║ ");

            for (Location location : datum) {
                var dataMap = location.getPopulations();
                output.append(formatValues(dataMap));
                output.append("║ ");
            }
            output.append("\n"); // Закрываем строку таблицы
        }
        System.out.print(output);
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
                String icon = " ";

                Optional<Biota> first = sets.get(i).stream().findFirst();
                if(first.isPresent()){
                    icon = first.get().getProperty().getIcon();
                }

                values.append(icon);
                values.append(String.format("%-4d", result));
            }
        }
        return values.toString();
    }
}
