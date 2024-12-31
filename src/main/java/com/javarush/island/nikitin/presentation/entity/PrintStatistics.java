package com.javarush.island.nikitin.presentation.entity;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//todo рефакторинг!
public class PrintStatistics {
    private static final String SYMBOL_INCREASE = "🟩";
    static String symbolDecrease = "🟥";
    static int sizeScale = 50;
    static int stepScale = 2;
    private static int FULL_SIZE_UNIT;
    EcoSystem ecoSystem;

    private final Map<String, Integer> statistic = new HashMap<>();

    public void printStatistic(EcoSystem ecoSystem) {
        this.ecoSystem = ecoSystem;
        //todo удалить проверку корректности дня екосистемы и дня у огранизма
        System.out.println("GeneralDay " + ecoSystem.getStartDate());
        Location[][] data = ecoSystem.getLocationsForView();
        for (Location[] datum : data) {
            for (Location location : datum) {
                var map = location.getPopulations();
                dataExtract(map);
            }
        }
        showStatistic();
    }

    private void dataExtract(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> community) {
        for (var entries : community.entrySet()) {
            String nameCommunity = entries.getKey();
            int sizeCommunity = entries.getValue().size();
            //todo удалить проверку корректности дня екосистемы и дня у огранизма
            /*entries.getValue().forEach(e -> {
                        int eco = ecoSystem.getStartDate();
                        int current = e.getCurrentDay();
                        if (eco != current) {
                            System.out.println(e);
                            throw new RuntimeException("Day - getStartDate " + eco + " getCurrentDay " + current);
                        }
                    }
            );

             */


            collectStatistics(sizeCommunity, nameCommunity);
        }
    }

    private void collectStatistics(int sizeCommunity, String nameCommunity) {
        int sizeAllCommunity = statistic.getOrDefault(nameCommunity, 0) + sizeCommunity;
        FULL_SIZE_UNIT += sizeCommunity;
        statistic.put(nameCommunity, sizeAllCommunity);
    }

    private void showStatistic() {
        System.out.println(statistic);
        System.out.println("Total pupulation in Island: " + FULL_SIZE_UNIT);
        if (FULL_SIZE_UNIT == 0) {
            return;
        }
        System.out.println("Statistics graph:");

        statistic.forEach(this::printStatistic);

        System.out.println("==========================================================================");
        resetStatistics();
    }

    private void resetStatistics() {
        statistic.clear();
        FULL_SIZE_UNIT = 0;
    }

    private void printStatistic(String name, int count) {
        double result = count * 100.0 / FULL_SIZE_UNIT;
        result = result >= 0.02 ? result : 0;
        int numberOfSymbols = (int) result / 2;

        System.out.printf("%-8s ", name);
        for (int i = 0; i < numberOfSymbols; i++) {
            if (i % 5 == 0) {
                System.out.print(" ");
            }
            System.out.print(SYMBOL_INCREASE);
        }
        System.out.printf(" %.2f%% (count: %d) \n", result, statistic.get(name));
    }
}
