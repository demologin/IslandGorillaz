package com.javarush.island.popov.services;

import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.units.Unit;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReproducingService implements Runnable{
    IslandMap map;

    public ReproducingService(IslandMap map) {
        this.map = map;
    }
    @Override
    public void run(){
        Cell[][] cells = map.getCells();
        for (Cell[] rows : cells) {
            for (Cell cell : rows) {cell.getLock().lock();
                try{
                for (Map.Entry<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> entry :
                        cell.getAllUnitsInCell().entrySet()) {
                    CopyOnWriteArrayList<Unit> unitList = entry.getValue();
                    for (Unit unit : unitList) {
                        unit.reproduce(cell);
                    }
                }
               }finally {
                    cell.getLock().unlock();
                }
            }
        }
    }

}
