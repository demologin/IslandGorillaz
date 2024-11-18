package com.javarush.island.myasnikov.view.console;


import com.javarush.island.myasnikov.map.Cell;
import com.javarush.island.myasnikov.map.GameMap;
import com.javarush.island.myasnikov.map.GameTask;
import com.javarush.island.myasnikov.utility.OrganismTypes;


import static com.javarush.island.myasnikov.config.Params.*;

public class ConsoleRunner {

    private static void printMap(Cell[][] cells) {
        printHorizontalBorder(cells.length);
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                System.out.print("| " + String.format("%-" + (CELL_VIEW_LIMIT) + "s", value.getOrganismsIcon()) + " ");
            }
            System.out.println("|");
            printHorizontalBorder(cells.length);
        }
        System.out.println();
    }

    private static void printHorizontalBorder(int columns) {
        System.out.print("+");
        for (int j = 0; j < columns; j++) {
            for (int k = 0; k < CELL_WIDTH; k++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        GameMap gameMap = new GameMap();
        Cell[][] gameMapCells = gameMap.getMap();

        for (OrganismTypes organism : OrganismTypes.values()) {
            Thread thread = new Thread(new GameTask("Task " + organism + "s", gameMap, organism));
            thread.start();
        }

        for (int i = 0; i < PLAY_DAYS; i++) {
            Thread.sleep(1000);
            printMap(gameMapCells);
            gameMap.getStatistics();
        }
    }
}

