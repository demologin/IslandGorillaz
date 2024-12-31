package com.javarush.island.nikitin.presentation;

import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.presentation.api.View;
import com.javarush.island.nikitin.presentation.entity.PrintStatistics;

public class ConsoleView implements View {

    @Override
    public void displayData(EcoSystem ecoSystem) {

        new PrintStatistics().printStatistic(ecoSystem);
        //new PrintCell().printCell(ecoSystem);

    }
}
