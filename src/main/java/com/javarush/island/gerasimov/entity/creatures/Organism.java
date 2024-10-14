package com.javarush.island.gerasimov.entity.creatures;

import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;

import com.javarush.island.gerasimov.intefaces.ReproductionAble;
import com.javarush.island.gerasimov.service.EntityService;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class Organism implements ReproductionAble {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;
    private String name;
    private String icon;
    private double weight;
    private int maxCountInCell;
    private int maxSpeed;
    private double maxFood;
    private volatile Cell currentCell;
    private volatile Cell targetCell;
    private GameMap gameMap = EntityService.gameMap;

    public Organism() {
        id = COUNTER.getAndIncrement();
    }

    public synchronized Cell appointmentTargetCell(Cell currentCell) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int ran = random.nextInt(this.getMaxSpeed()+1);
        currentCell = this.getCurrentCell();
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        targetCell = currentCell;
        try {
            if (row > ran) {
                targetCell = gameMap.getCells()
                        [row - ran]
                        [col];
            } else if (col > ran) {
                targetCell = gameMap.getCells()
                        [row]
                        [col - ran];
            } else if (ran < gameMap.getRows() - row) {
                targetCell = gameMap.getCells()
                        [row + ran]
                        [col];
            } else if (ran < gameMap.getCols() - col) {
                targetCell = gameMap.getCells()
                        [row]
                        [col + ran];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setTargetCell(targetCell);
        return targetCell;
    }
}

