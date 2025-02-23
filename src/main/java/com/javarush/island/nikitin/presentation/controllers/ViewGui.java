package com.javarush.island.nikitin.presentation.controllers;

import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.presentation.entity.DataCell;
import com.javarush.island.nikitin.presentation.entity.DataStatistic;
import com.javarush.island.nikitin.presentation.view.Printer;
import com.javarush.island.nikitin.presentation.view.PrinterGui;

public class ViewGui extends AbstractView {
    private final Printer printer;

    public ViewGui() {
        this.printer = new PrinterGui();
    }

    public void displayData(DataContainer dataContainer) {
        DataStatistic dataStatistic = computeStatistics(dataContainer);
        DataCell dataCell = computeCell(dataContainer);
        String stringStat = statisticToString(dataStatistic);
        String stringCell = cellToString(dataCell);

        printer.print(stringStat, stringCell);

    }
}
