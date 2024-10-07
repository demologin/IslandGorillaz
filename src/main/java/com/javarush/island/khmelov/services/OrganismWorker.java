package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.GameMap;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.Organisms;
import com.javarush.island.khmelov.exception.GameException;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrganismWorker implements Runnable {

    private final Organism prototype;
    private String type;
    private final GameMap gameMap;
    private final Queue<Task> tasks = new ConcurrentLinkedQueue<>();

    public OrganismWorker(Organism prototype, GameMap gameMap) {
        this.prototype = prototype;
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        type = prototype.getType();
        Cell[][] cells = gameMap.getCells();
        for (int i = 0, rowsCount = cells.length; i < rowsCount; i++) {
            Cell[] row = cells[(i + hashCode()) % rowsCount];
            for (Cell cell : row) {
                try {
                    if (!cell.getResidents().get(type).isEmpty()) {
                        processOneCell(cell);
                    }
                } catch (Exception e) {
                    throw new GameException("Debug it!", e);
                }
            }
        }
    }

    private void processOneCell(Cell cell) {
        Organisms organisms = cell.getResidents().get(type);
        if (Objects.nonNull(organisms)) {
            //build tasks (need correct iteration, without any modification)
            cell.getLock().lock(); //ONLY READ
            try {
                organisms.forEach(organism -> {
                    //here possible action-cycle for entity (enum, collection or array)
                    tasks.add(new Task(organism, cell));
                });
            } finally {
                cell.getLock().unlock();
            }

            //run tasks
            //see CQRS pattern or CommandBus pattern and Producer-Consumer problem.
            //This cycle can to run in other thread or threads (pool)
            tasks.forEach(Task::doTask);
            tasks.clear();
        }
    }
}
