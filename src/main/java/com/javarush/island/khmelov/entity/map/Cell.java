package com.javarush.island.khmelov.entity.map;

import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.util.Rnd;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cell {

    private final List<Cell> nextCell = new ArrayList<>();

    @Getter
    private final Residents residents = new Residents();

    public Object monitor() {
        return this;
    }

    void updateNextCell(GameMap map, int row, int col) {
        if (row > 0) nextCell.add(map.getCell(row - 1, col));
        if (row < map.getRows() - 1) nextCell.add(map.getCell(row + 1, col));
        if (col > 0) nextCell.add(map.getCell(row, col - 1));
        if (col < map.getCols() - 1) nextCell.add(map.getCell(row, col + 1));
    }

    public Cell getNextCell(int countStep) {
        Set<Cell> visitedCells = new HashSet<>();
        Cell currentCell = this;
        while (visitedCells.size() < countStep) {
            var nextCells = currentCell
                    .nextCell
                    .stream()
                    .filter(cell -> !visitedCells.contains(cell))
                    .toList();
            int countDirections = nextCells.size();
            if (countDirections > 0) {
                int index = Rnd.random(0, countDirections);
                currentCell = nextCells.get(nextCells.size() - 1 - index);
                visitedCells.add(currentCell);
            } else {
                break;
            }
        }
        return currentCell;
    }

    @Override
    public String toString() {
        return getResidents().values().stream()
                .filter((list) -> !list.isEmpty())
                .sorted((o1, o2) -> o2.size() - o1.size())
                .map(set -> set
                        .stream()
                        .findAny()
                        .map(Organism::getLetter)
                        .orElse("?"))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}

