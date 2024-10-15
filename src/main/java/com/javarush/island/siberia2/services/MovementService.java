package com.javarush.island.siberia2.services;

import com.javarush.island.siberia.utils.RandomUtils;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.map.Cell;

import java.util.List;

public class MovementService {

    public void move(Animal animal) {
        int speed = animal.getSettings().getSpeed();
        Cell currentCell = animal.getCurrentCell();

        for (int i = 0; i < speed; i++) {
            List<Cell> adjacentCells = currentCell.getAdjacentCells();

            List<Cell> possibleCells = adjacentCells.stream()
                    .filter(cell -> !cell.isWater())
                    .toList();

            if (!possibleCells.isEmpty()) {
                Cell nextCell = possibleCells
                        .get(RandomUtils.randomInt(0, possibleCells.size() -1));

                currentCell.removeAnimal(animal);
                nextCell.addAnimal(animal);
                animal.setCurrentCell(nextCell);
                currentCell = nextCell;
            }
        }
    }
}