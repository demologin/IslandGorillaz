package com.javarush.island.nikitin.application;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.services.EcoSystemService;
import com.javarush.island.nikitin.application.services.PreparationService;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.presentation.api.View;

// TODO запуск приложения. Конструктор принимает объект вида , с которым будет работат - консоль или SWING
public class AppRunner {
    private final View view;

    public AppRunner(View view) {
        this.view = view;
    }

    public void start() {
        Settings settings = Settings.newInstance();

        var island = new Island(settings.getRows(), settings.getColumns());
        var ecoSystem = new EcoSystem(island, settings.getStartDate());
        var preparationService = new PreparationService(settings, island);

        preparationService.make();

        var ecoSystemService = new EcoSystemService(ecoSystem, view);

        ecoSystemService.simulateDay(settings.getPeriodLive());
    }
}
