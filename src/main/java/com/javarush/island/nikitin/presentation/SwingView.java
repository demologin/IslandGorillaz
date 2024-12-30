package com.javarush.island.nikitin.presentation;

import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.presentation.api.View;

public class SwingView implements View {
    public void displayData(EcoSystem ecoSystem) {
        System.out.println("I am SwingView implements me");
    }
}
