package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class StartLife extends Thread {

    private Organism organism;
    private volatile Cell currentCell;
    private GameMap gameMap = EntityService.gameMap;
    ;

    public Cell getCurrentCell() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomRow = random.nextInt(20);
        int randomCol = random.nextInt(100);
        Cell cell = gameMap.getCells()[randomRow][randomCol];
        cell.setCol(randomCol);
        cell.setRow(randomRow);
        this.currentCell = cell;
        return cell;
    }

    private Organism getCurrentOrganism() {
        currentCell = getCurrentCell();
        List<Organism> organisms = currentCell.getOrganisms();
        Organism organism = organisms.get((int) (Math.random() * organisms.size()));
        organism.setCurrentCell(currentCell);
        organism.setGameMap(gameMap);
        return organism;
    }

    @Override
    public void run() {
        try {
            organism = getCurrentOrganism();
            if (organism instanceof Animal animal) {
                if (animal.move(this.currentCell)) {
                    if (animal.eat()) {
                        animal.reproduce(this.currentCell);
                    }
                }
            } else if (organism instanceof Plant plant) {
                plant.reproduce(currentCell);
            }
        } catch (Exception e) {
            e.getCause();
        }
    }
}
