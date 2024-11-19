package com.javarush.island.nikitin.presentation;

import com.javarush.island.nikitin.presentation.api.View;
import com.javarush.island.nikitin.domain.entity.map.Location;

public class SwingView implements View {
    public void displayData(Location[][] data) {
        System.out.println("I am SwingView implements me");
    }
}
