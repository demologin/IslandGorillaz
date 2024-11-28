package com.javarush.island.popov.view;

import com.javarush.island.popov.creators.StatisticsCreator;
import com.javarush.island.popov.interfaces.view.View;
import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.units.Unit;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConsoleView implements View {

    @Override
    public void showStaticticByUnits(StatisticsCreator statistics, IslandMap map) {
        for (Map.Entry<String,Integer> entry:statistics.getStatisticByUnits(map).entrySet()){
            System.out.print(entry.getKey()+ "=" + entry.getValue()+" ");
        }
        //System.out.println();
    }

    @Override
    public void showStaticticByCells(IslandMap map) {
        int countCell = 0;
        Cell[][] cells = map.getCells();
        for (Cell[] rows : cells) {
            for (Cell cell : rows) {
                countCell++;
                boolean cellIsEmpty=true;
                if (cellIsEmpty) {System.out.printf("\n%d cell:\n", countCell);
                }else {
                System.out.printf("%d cell: ", countCell);}
                for (Map.Entry<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> entry :
                        cell.getAllUnitsInCell().entrySet()) {
                    if (!entry.getValue().isEmpty()){
                        cellIsEmpty = false;
                        System.out.print("{"+ entry.getKey().getSimpleName() + ":" + entry.getValue().getFirst().getIcon()+ "}="
                                + entry.getValue().size()+", ");
                    }
                }
                if (cellIsEmpty) {System.out.print("Cell is empty");}
            }
        }


    }

}

