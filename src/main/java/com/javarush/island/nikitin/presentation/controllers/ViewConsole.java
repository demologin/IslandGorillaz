package com.javarush.island.nikitin.presentation.controllers;

import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.presentation.entity.DataCell;
import com.javarush.island.nikitin.presentation.view.Printer;
import com.javarush.island.nikitin.presentation.entity.DataStatistic;
import com.javarush.island.nikitin.presentation.view.PrinterConsole;


public class ViewConsole extends AbstractView {
    private final Printer printer;

    public ViewConsole() {
        this.printer = new PrinterConsole();
    }

    @Override
    public void displayData(DataContainer dataContainer) {
        DataStatistic dataStatistic = computeStatistics(dataContainer);
        DataCell dataCell = computeCell(dataContainer);
        String stringStat = statisticToString(dataStatistic);
        String stringCell = cellToString(dataCell);


        printer.print(stringStat);
        printer.print(stringCell);
        //new PrintCell().printCell(ecoSystem);
    }
}
