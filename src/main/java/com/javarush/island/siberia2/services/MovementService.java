package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.util.RandomUtils;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;
import java.util.List;

public class MovementService {

    private final AdjacentCellService adjacentCellService = new AdjacentCellService();

    public void move(Animal animal) {
        animal.getLock().lock();
        try {
            int speed = animal.getSettings().getSpeed();
            Cell currentCell = animal.getCurrentCell();

            for (int i = 0; i < speed; i++) {
                List<Cell> adjacentCells = adjacentCellService.getAdjacentCells(currentCell);

                List<Cell> possibleCells = adjacentCells.stream()
                        .filter(cell -> !cell.isWater())
                        .toList();

                if (!possibleCells.isEmpty()) {
                    Cell nextCell = possibleCells
                            .get(RandomUtils.randomInt(0, possibleCells.size() - 1));

                    currentCell.removeAnimal(animal);
                    nextCell.addAnimal(animal);
                    animal.setCurrentCell(nextCell);
                    currentCell = nextCell;
                }
            }
        } finally {
            animal.getLock().unlock();
        }
    }
}