package com.javarush.island.nikitin.presentation.services;

import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.presentation.entity.DataCell;
import com.javarush.island.nikitin.presentation.entity.DataStatistic;

import java.util.Map;
import java.util.function.Function;

public class DataFactory {

    public DataStatistic newDataStatistic(DataContainer data,
                                          Function<DataContainer, Map<String, Integer>> computerStatistics,
                                          Function<Map<String, Integer>, Integer> computerCount) {

        Map<String, Integer> statisticMap = computerStatistics.apply(data);
        Integer countAllResidents = computerCount.apply(statisticMap);
        return new DataStatistic(statisticMap,
                countAllResidents,
                data.currentCalendarDay(),
                data.deathTotalLocation(),
                data.allRows(),
                data.allColumns());
    }

    public DataCell newDataCell(DataContainer data){
        int rowsPrint = data.rowsPrint();
        int columnsPrint = data.columnsPrint();
        int countBiotaPrint = data.countBiotaPrint();
        return new DataCell(data.locations(),
                rowsPrint,
                columnsPrint,
                countBiotaPrint);
    }
}
