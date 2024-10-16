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
    private  Cell currentCell;
    private GameMap gameMap = EntityCreator.gameMap;

    public Cell getCurrentCell() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomRow = random.nextInt(20);
        int randomCol = random.nextInt(100);
        Cell cell = gameMap.getCells()[randomRow][randomCol];
        cell.setCol(randomCol);
        cell.setRow(randomRow);
        return cell;
    }

    private Organism getCurrentOrganism() {
        currentCell = getCurrentCell();
        List<Organism> organisms = currentCell.getOrganisms();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int ran = random.nextInt(organisms.size());
        Organism organism = organisms.get(ran);
        organism.setCurrentCell(currentCell);
//        organism.setGameMap(gameMap);
        return organism;
    }

    @Override
    public void run() {
        try {
            organism = getCurrentOrganism();
            if (organism instanceof Animal animal) {
                if (animal.move(currentCell)) {
                    if (animal.eat()) {
                        animal.reproduce(currentCell);
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
