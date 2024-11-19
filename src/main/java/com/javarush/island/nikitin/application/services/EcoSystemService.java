package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.presentation.api.View;

public class EcoSystemService {
    private final EcoSystem ecoSystem;
    private final View view;

    public EcoSystemService(EcoSystem ecoSystem, View view) {
        this.ecoSystem = ecoSystem;
        this.view = view;
    }

    public void simulateDay() {
        notifyView();
        for (int i = 0; i < 10; i++) {
            ecoSystem.act();
            notifyView();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void notifyView() {
        view.displayData(ecoSystem.getIsland().getLocation());
    }


}
