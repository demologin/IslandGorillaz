package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;

/**
 * Generates an object for transfer to the presentation layer
 */
public class DataService {

    public DataContainer getData(EcoSystem ecoSystem, Settings settings) {
        Location[][] locationsForView = ecoSystem.getLocationsForView();
        int calendarDay = ecoSystem.getCalendarDay();
        long numberDeathsDay = ecoSystem.getNumberDeathsDay();
        int allRows = settings.getRows();
        int allColumns = settings.getColumns();
        int rowsPrint = settings.getRowsPrint();
        int columnsPrint = settings.getColumnsPrint();
        int countBiotaPrint = settings.getCountBiotaPrint();

        return new DataContainer(locationsForView,
                calendarDay,
                numberDeathsDay,
                allRows,
                allColumns,
                rowsPrint,
                columnsPrint,
                countBiotaPrint);
    }
}
