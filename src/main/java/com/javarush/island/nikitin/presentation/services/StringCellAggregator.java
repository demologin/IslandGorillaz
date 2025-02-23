package com.javarush.island.nikitin.presentation.services;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.presentation.entity.DataCell;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StringCellAggregator {
    private final StringBuilder stringBuilder;

    public StringCellAggregator() {
        this.stringBuilder = new StringBuilder();
    }

    public String getString() {
        String string = stringBuilder.toString();
        stringBuilder.setLength(0);
        return string;
    }

    public void collectCell(DataCell dataCell){
        Location[][] locations = dataCell.locations();
        int rowsPrint = dataCell.rowsPrint();
        int columnsPrint = dataCell.columnsPrint();
        int countBiotaPrint = dataCell.countBiotaPrint();

        rowsPrint = Math.min(rowsPrint, locations.length);

        for (int i = 0; i < rowsPrint; i++) {
            stringBuilder.append("║ ");
            columnsPrint = Math.min(columnsPrint, locations[i].length);
            for (int j = 0; j < columnsPrint; j++) {
                var dataMap = locations[i][j].getAllPopulationsLocation();
                formatValues(dataMap, countBiotaPrint);
                stringBuilder.append("║ ");
            }
            stringBuilder.append("\n");
        }

    }
    private void formatValues(Map<String, ConcurrentHashMap.KeySetView<Biota, Boolean>> map, int countBiotaPrint) {
        List<Set<Biota>> sets = List.copyOf(map.values());
        for (int i = 0; i < countBiotaPrint; i++) {
            int result = 0;
            if (i < sets.size()) {
                result = sets.get(i).size();
            }
            if (result == 0) {
                stringBuilder.append("  ");
                stringBuilder.append(String.format("%-4s", ""));
            } else {
                String icon = " ";

                Optional<Biota> first = sets.get(i).stream().findFirst();
                if(first.isPresent()){
                    icon = first.get().getProperty().getIcon();
                }
                stringBuilder.append(icon);
                stringBuilder.append(String.format("%-4d", result));
            }
        }
    }
}
