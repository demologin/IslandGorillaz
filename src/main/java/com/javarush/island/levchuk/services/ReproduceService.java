package com.javarush.island.levchuk.services;


import com.javarush.island.levchuk.map.Cell;

import java.util.List;


public class ReproduceService {

    public void reproduceAllInCall(Cell cell) {
        cell.getResidents().values().stream()
                .flatMap(List::stream)
                .forEach(entity -> {
                    cell.getLock().lock();
                    int countOfParentsPair = cell.getResidents()
                            .get(entity.getClass()).size() / 2;
                    if (countOfParentsPair > 0 && cell.checkFreeSpace(entity)) {
                        for (int i = 0; i < countOfParentsPair; i++) {
                            entity.reproduce(cell);
                        }
                    }
                });
    }
}
