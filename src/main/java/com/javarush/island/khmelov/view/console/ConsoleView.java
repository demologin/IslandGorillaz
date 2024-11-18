package com.javarush.island.khmelov.view.console;

import com.javarush.island.khmelov.api.view.View;
import com.javarush.island.khmelov.config.Console;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.GameMap;

import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.javarush.island.khmelov.view.console.Symbols.*;

public class ConsoleView implements View {

    private int rows;
    private int cols;
    private final boolean cutRows;
    private final boolean cutCols;

    private final GameMap gameMap;
    private final int cellCharCount;
    private final String border;
    private final String topBorder;
    private final String centerBorder;
    private final String bottomBorder;
    private final String bottomInfBorder;

    public ConsoleView(GameMap gameMap) {
        final Console console = Setting.get().console;
        this.gameMap = gameMap;

        cellCharCount = console.getCellCharCount();
        border = "═".repeat(cellCharCount);

        int showRows = console.getShowRows();
        rows = gameMap.getRows();
        cutRows = rows > showRows;
        rows = cutRows ? showRows : rows;

        int showCols = console.getShowCols();
        cols = gameMap.getCols();
        cutCols = cols > showCols;
        cols = cutCols ? showCols : cols;

        topBorder = border(cols, LEFT_TOP, TOP, RIGHT_TOP);
        centerBorder = border(cols, LEFT, CENTER, RIGHT);
        bottomBorder = border(cols, LEFT_BOTTOM, CENTER_BOTTOM, RIGHT_BOTTOM);
        bottomInfBorder = String.valueOf(INF_MARGIN).repeat(((cellCharCount + 1) * showCols) + 1);
    }


    @Override
    public void show() {
        showMap();
        showStatistics();
        showScale();
    }

    @Override
    public void showStatistics() {
        System.out.println(gameMap.getStatistics());
    }

    @Override
    public void showScale() {
        int n = 100;
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 10; i <= n; i += 10) {
            String color = Color.getColor(i, n);
            joiner.add(color + i + "%" + Color.RESET);
        }
        System.out.println("Scale: " + joiner);
    }

    @Override
    public void showMap() {
        StringBuilder out = new StringBuilder();
        Cell[][] cells = gameMap.getCells();
        for (int row = 0; row < rows; row++) {
            out.append(row == 0 ? topBorder : centerBorder).append(LINE_BREAK);
            for (int col = 0; col < cols; col++) {
                String residentSting = getResidentSting(cells[row][col]);
                out.append(String.format(CELL_MARGIN + "%-" + cellCharCount + "s", residentSting));
            }
            out.append(cutCols ? INF_MARGIN : CELL_MARGIN).append(LINE_BREAK);
        }
        out.append(cutRows ? bottomInfBorder : bottomBorder).append(LINE_BREAK);
        System.out.println(out);
    }

    private String getResidentSting(Cell cell) {
        cell.getLock().lock();
        String collect = cell.getResidents().values().stream()
                .filter((list) -> !list.isEmpty())
                .sorted((o1, o2) -> o2.size() - o1.size())
                .limit(cellCharCount)
                .map(organisms -> Color.getColor(organisms.size(), organisms.getLimit().getMaxCountInCell())
                                  + organisms.getLetter()
                                  + Color.RESET
                )
                .map(Object::toString)
                .collect(Collectors.joining());
        long count = cell
                .getResidents()
                .values()
                .stream()
                .filter((list) -> !list.isEmpty())
                .limit(cellCharCount)
                .count();
        String blank = count < cellCharCount ? DOT.repeat((int) (cellCharCount - count)) : BLANK;
        cell.getLock().unlock();
        return collect + blank;
    }

    private String border(int cols, char left, char center, char right) {
        right = cutCols ? INF_MARGIN : right;
        return (IntStream.range(0, cols)
                .mapToObj(col -> (col == 0 ? left : center) + border)
                .collect(Collectors.joining(BLANK, BLANK, String.valueOf(right))));
    }
}
