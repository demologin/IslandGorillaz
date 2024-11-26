package com.javarush.island.popov.services;

import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.units.Unit;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EatingService implements Runnable {
    IslandMap map;

    public EatingService(IslandMap map) {
        this.map = map;
    }
    @Override
    public void run(){
        Cell[][] cells = map.getCells();
        for (Cell[] rows : cells) {
            for (Cell cell : rows) {
                for (Map.Entry<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> entry : cell.getAllUnitsInCell().entrySet()) {
                    CopyOnWriteArrayList<Unit> listUnit = entry.getValue();
                    Iterator<Unit> iterator = listUnit.iterator();
                    while (iterator.hasNext()) {
                        Unit unit = iterator.next();
                        unit.eat(cell);
                    }
                }
            }
        }
    }
}
