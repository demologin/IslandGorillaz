package com.javarush.island.nikitin.application;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.controllers.AppController;
import com.javarush.island.nikitin.application.entity.SurvivalGame;
import com.javarush.island.nikitin.application.exception.AppException;
import com.javarush.island.nikitin.application.services.EcoSystemService;
import com.javarush.island.nikitin.application.services.PreparationService;
import com.javarush.island.nikitin.domain.exception.DomainException;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;

public class AppRunner {
    private final AppController appController;

    public AppRunner(AppController appController) {
        this.appController = appController;
    }

    public void start() {
        try {
            var settings = Settings.newInstance();
            var survivalGame = new SurvivalGame(settings);
            var preparationService = new PreparationService(settings, survivalGame);

            EcoSystem ecoSystem = preparationService.setupEcoSystem();
            var ecoSystemService = new EcoSystemService(ecoSystem, appController, settings);
            ecoSystemService.beginSurvival();

        } catch (AppException | DomainException e) {
            System.out.println(e.getMessage());
        }
    }
}
