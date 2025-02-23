package com.javarush.island.gerasimov.service;

import com.javarush.island.gerasimov.constants.Constants;
import com.javarush.island.gerasimov.entity.creatures.Animal;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.Plant;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;
import com.javarush.island.gerasimov.repository.EntityCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class StartLifeService extends Thread {

    private GameMap gameMap = EntityCreator.gameMap;

    @Override
    public void run() {
        try {
            Organism organism = getCurrentOrganism();
            if (organism instanceof Animal animal) {
                if (animal.move()) {
                    if (animal.eat()) {
                        animal.reproduce();
                    }
                }
            } else if (organism instanceof Plant plant) {
                plant.reproduce();
            }
        } catch (Exception e) {
            e.getCause();
        }
    }

    public Cell getCurrentCell() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomRow = random.nextInt(Constants.MAP_ROW);
        int randomCol = random.nextInt(Constants.MAP_COL);
        Cell cell = gameMap.getCells()[randomRow][randomCol];
        cell.setCol(randomCol);
        cell.setRow(randomRow);
        return cell;
    }

    private Organism getCurrentOrganism() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Cell currentCell = getCurrentCell();

        List<Organism> organisms = currentCell.getOrganisms();
        int ran = random.nextInt(organisms.size());
        Organism organism = organisms.get(ran);

        organism.setCurrentCell(currentCell);
        organism.setTargetCell(organism.appointmentTargetCell(currentCell));
        return organism;
    }
}
