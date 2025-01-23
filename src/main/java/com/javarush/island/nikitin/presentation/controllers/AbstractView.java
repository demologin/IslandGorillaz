package com.javarush.island.nikitin.presentation.controllers;

import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.presentation.entity.DataCell;
import com.javarush.island.nikitin.presentation.entity.DataStatistic;
import com.javarush.island.nikitin.presentation.services.DataFactory;
import com.javarush.island.nikitin.presentation.services.DataProcessing;
import com.javarush.island.nikitin.presentation.services.StringCellAggregator;
import com.javarush.island.nikitin.presentation.services.StringStatAggregator;

public abstract class AbstractView implements View {
    private final DataFactory dataFactory;
    private final DataProcessing dataProcessing;
    private final StringStatAggregator stringStatAggregator;
    private final StringCellAggregator stringCellAggregator;

    public AbstractView() {
        this.dataProcessing = new DataProcessing();
        this.stringStatAggregator = new StringStatAggregator();
        this.dataFactory = new DataFactory();
        this.stringCellAggregator = new StringCellAggregator();
    }

    public String statisticToString(DataStatistic dataStatistic) {
        stringStatAggregator.appendLineSeparator();
        stringStatAggregator.appendHeader(dataStatistic);
        stringStatAggregator.appendDiagram(dataStatistic);
        return stringStatAggregator.getString();
    }

    public DataStatistic computeStatistics(DataContainer dataContainer) {
        return dataFactory.newDataStatistic(dataContainer,
                dataProcessing::composeStatistics,
                dataProcessing::countAllResidents);
    }
    public DataCell computeCell(DataContainer dataContainer){
        return dataFactory.newDataCell(dataContainer);
    }

    public String cellToString(DataCell dataCell){
        stringCellAggregator.collectCell(dataCell);
        return stringCellAggregator.getString();
    }
}
