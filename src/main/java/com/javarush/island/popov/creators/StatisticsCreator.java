package com.javarush.island.popov.creators;

import com.javarush.island.popov.map.Cell;
import com.javarush.island.popov.map.IslandMap;
import com.javarush.island.popov.units.Unit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class StatisticsCreator {

    public Map<String,Integer> getStatisticByUnits(IslandMap map){
        Map<String,Integer> statisticMap = new LinkedHashMap<>();
        Cell[][] cells = map.getCells();
        for (Cell[] rows : cells) {
            for (Cell cell: rows) {
                Map<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> unitsInCell = cell.getAllUnitsInCell();
                for (Map.Entry<Class<? extends Unit>, CopyOnWriteArrayList<Unit>> entry:
                    unitsInCell.entrySet()){
                     if (entry.getKey()!=null && !entry.getValue().isEmpty()) {
                         String simpleName = entry.getKey().getSimpleName();
                         String icon = entry.getValue().getFirst().getIcon();
                         String name = "{" + simpleName + ":" + icon + "}";
                         int countUnitsInCell = entry.getValue().size();
                         if (!statisticMap.containsKey(name)) {
                             statisticMap.put(name, countUnitsInCell);
                         } else {
                             statisticMap.put(name, statisticMap.get(name) + countUnitsInCell);
                         }
                     }
                }

            }
        }
        return statisticMap;
    }

}
