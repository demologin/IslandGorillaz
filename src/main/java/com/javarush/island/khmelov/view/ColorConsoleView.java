package com.javarush.island.khmelov.view;

import com.javarush.island.khmelov.api.view.View;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.entity.map.ResidentMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SuppressWarnings("FieldCanBeLocal")
public class ColorConsoleView implements View {

    private final int showRows;
    private final int showCols;
    private int rows;
    private int cols;
    private final boolean cutRows;
    private final boolean cutCols;

    private final GameMap gameMap;
    private final int cellWidth = Setting.get().getConsoleCellWith();
    private final String border = "═".repeat(cellWidth);

    public ColorConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;

        showRows = Setting.get().getShowRows();
        rows = gameMap.getRows();
        cutRows = rows > showRows;
        rows = cutRows ? showRows : rows;

        showCols = Setting.get().getShowCols();
        cols = gameMap.getCols();
        cutCols = cols > showCols;
        cols = cutCols ? showCols : cols;
    }


    @Override
    public void show() {
        System.out.println(showScale() + showMap() + showStatistics());
    }

    @Override
    public String showStatistics() {
        Map<String, Double> rawStatistics = new HashMap<>();
        Map<String, Long> statistics = new TreeMap<>();
        Cell[][] cells = gameMap.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                ResidentMap residents = cell.getResidents();
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
        rawStatistics.forEach((key, value) -> statistics.put(key, (long) Math.ceil(value)));
        return statistics + "\n";
    }

    @Override
    public String showScale() {
        int n = 100;
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 10; i <= n; i += 10) {
            String color = Color.getColor(i, n);
            joiner.add(color + i + "%" + Color.RESET);
        }
        return "Scale: " + joiner + "\n";
    }

    @Override
    public String showMap() {
        StringBuilder out = new StringBuilder();
        Cell[][] cells = gameMap.getCells();
        for (int row = 0; row < rows; row++) {
            out.append(row == 0
                    ? line(cols, Symbols.LEFT_TOP, Symbols.TOP, Symbols.RIGHT_TOP)
                    : line(cols, Symbols.LEFT, Symbols.CENTER, Symbols.RIGHT)
            ).append("\n");
            for (int col = 0; col < cols; col++) {
                String residentSting = get(cells[row][col]);
                out.append(String.format(Symbols.CELL_MARGIN + "%-" + cellWidth + "s", residentSting));
            }
            out.append(cutCols ? Symbols.INF_MARGIN : Symbols.CELL_MARGIN)
                    .append("\n");
        }
        if (!cutCols) {
            out.append(line(cols, Symbols.LEFT_BOTTOM, Symbols.CENTER_BOTTOM, Symbols.RIGHT_BOTTOM))
                    .append("\n");
        } else {
            out.append(String.valueOf(Symbols.INF_MARGIN).repeat(((cellWidth + 1) * showCols) + 1))
                    .append("\n");
        }
        return out.toString();
    }

    private String get(Cell cell) {
        cell.getLock().lock();
        String collect = cell.getResidents().values().stream()
                .filter((list) -> !list.isEmpty())
                .sorted((o1, o2) -> o2.size() - o1.size())
                .limit(cellWidth)
                .map(organisms -> {
                    int maxCount = organisms.getLimit().getMaxCountInCell();
                    String color = Color.getColor(organisms.size(), maxCount);
                    return color + organisms.getLetter() + Color.RESET;
                })
                .map(Object::toString)
                .collect(Collectors.joining());
        long count = cell
                .getResidents()
                .values()
                .stream()
                .filter((list) -> !list.isEmpty())
                .limit(cellWidth)
                .count();
        String blank = count < cellWidth ? ".".repeat((int) (cellWidth - count)) : "";
        cell.getLock().unlock();
        return collect + blank;
    }

    private String line(int cols, char left, char center, char right) {
        right = cutCols ? Symbols.INF_MARGIN : right;
        return (IntStream.range(0, cols)
                .mapToObj(col -> (col == 0 ? left : center) + border)
                .collect(Collectors.joining("", "", String.valueOf(right))));
    }
}
