package com.javarush.island.khmelov.services;

import com.javarush.island.khmelov.entity.Game;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.Organisms;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

abstract class AbstractService implements Runnable {

    protected final Game game;

    protected AbstractService(Game game) {
        this.game = game;
    }

    protected void processOneCell(Cell cell, Consumer<Organism> action) {
        var all = safeReadAll(cell);
        all.forEach(action);
    }

    private Set<Organism> safeReadAll(Cell cell) {
        cell.getLock().lock();
        try {
            return cell.getResidents()
                    .values()
                    .stream()
                    .flatMap(Organisms::stream)
                    .collect(Collectors.toSet());
        } finally {
            cell.getLock().unlock();
        }
    }
}
