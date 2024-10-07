package com.javarush.island.khmelov.entity.map;

import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.organizm.Organism;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class GameMap {
    private final Cell[][] cells;

    private final Map<Organism, Long> statistics;

    public GameMap(int rows, int cols) {
        this.cells = new Cell[rows][cols];
        statistics = new LinkedHashMap<>();
    }

    public int getRows() {
        return cells.length;
    }

    public int getCols() {
        return cells[0].length;
    }


    public void updateStatistics() {
        Map<String, Double> rawStatistics = new HashMap<>();
        Cell[][] cells = getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Residents residents = cell.getResidents();
                if (Objects.nonNull(residents)) {
                    residents.randomRotateResidents();
                    residents.values().stream()
                            .filter(organisms -> !organisms.isEmpty())
                            .forEach(organisms -> {
                                        String icon = organisms.getIcon();
                                        double count = organisms.calculateSize();
                                        rawStatistics.put(icon, rawStatistics.getOrDefault(icon, 0D) + count);
                                    }
                            );
                }
            }
        }
        for (Organism organism : Setting.PROTOTYPES) {
            long count = (long) Math.ceil(rawStatistics.getOrDefault(organism.getIcon(), 0d));
            if (count > 0) {
                statistics.put(organism,count);
            } else {
                statistics.remove(organism);
            }
        }
    }

}
