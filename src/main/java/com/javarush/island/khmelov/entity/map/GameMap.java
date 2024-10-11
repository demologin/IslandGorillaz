package com.javarush.island.khmelov.entity.map;

import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.organizm.Organism;
import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

public class GameMap {
    private final Cell[][] cells;

    @Getter
    private final Map<Organism, Long> statistics;

    public GameMap(int rows, int cols) {
        this.cells = new Cell[rows][cols];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].updateNextCell(this, i, j);
            }
        }
        statistics = new LinkedHashMap<>();
    }

    public Stream<Cell> getStreamCells() {
        return Arrays.stream(cells) //row
                .flatMap(Arrays::stream); //cells in row
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getRows() {
        return cells.length;
    }

    public int getCols() {
        return cells[0].length;
    }


    public void updateStatistics() {
        Map<String, Double> rawStatistics = new HashMap<>();
        getStreamCells().forEach(cell -> {
            synchronized (cell.monitor()) {
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
        });

        for (Organism organism : Setting.PROTOTYPES) {
            long count = (long) Math.ceil(rawStatistics.getOrDefault(organism.getIcon(), 0d));
            if (count > 0) {
                statistics.put(organism, count);
            } else {
                statistics.remove(organism);
            }
        }
    }

}
