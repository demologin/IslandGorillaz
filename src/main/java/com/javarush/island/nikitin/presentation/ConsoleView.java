package com.javarush.island.nikitin.presentation;

import com.javarush.island.nikitin.StartBeta;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.presentation.api.View;
import com.javarush.island.nikitin.presentation.entity.PrintCell;
import com.javarush.island.nikitin.presentation.entity.PrintStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConsoleView implements View {

    @Override
    public void displayData(Location[][] data) {
        System.out.println("I am ConsoleView implements me");
        System.out.println("==============================================================");

        new PrintStatistics().printStatistic(data);
        new PrintCell().printCell(data);

    }
}
