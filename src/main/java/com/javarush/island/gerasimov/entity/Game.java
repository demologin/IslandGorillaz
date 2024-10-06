package com.javarush.island.gerasimov.entity;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;
import com.javarush.island.gerasimov.service.CreatorCell;
import com.javarush.island.gerasimov.service.CreatorOrganism;
import java.util.Iterator;

public class Game extends Thread {
    private static final Cell[][] cells = new Cell[2][2];
    private static final CreatorCell creatorCell = new CreatorCell();
    private static final CreatorOrganism creatorOrganism = new CreatorOrganism();
    private static GameMap gameMap;


    public void addOrganisms() throws InterruptedException {
        creatorCell.initialisation();
        creatorOrganism.initialisation();

        int a = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cells[i][j] = creatorCell.getCells().get(a);
                a++;
            }
        }
        int b = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cells[i][j].setOrganisms(creatorOrganism.getOrganismList().get(b));
                b++;
            }
        }
        gameMap = new GameMap(cells);
        System.out.println("До изменения: " + gameMap);
    }

    public void move() {
        int randomRowStartCell = (int) (Math.random() * 2);
        int randomColStartCell = (int) (Math.random() * 2);
        Cell startCell = gameMap.getCells()[randomRowStartCell][randomColStartCell];

        int randomRowTargetCell = (int) (Math.random() * 2);
        int randomColTargetCell = (int) (Math.random() * 2);
        Cell targetCell = gameMap.getCells()[randomRowTargetCell][randomColTargetCell];
        if (startCell != null) {
            Iterator<Organism> iterator = startCell.getOrganisms().iterator();
            while (iterator.hasNext()) {
                if (!(randomColStartCell == randomColTargetCell && randomRowStartCell == randomRowTargetCell)) {
                    targetCell.getOrganisms().add(iterator.next());
                    iterator.remove();
                }
                break;
            }
        }
        System.out.println("После изменения: " + gameMap);
    }

    @Override
    public void run() {
        move();
    }
}
