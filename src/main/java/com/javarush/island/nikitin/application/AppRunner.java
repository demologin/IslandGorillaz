package com.javarush.island.nikitin.application;

import com.javarush.island.nikitin.application.config.DefaultSettings;
import com.javarush.island.nikitin.application.services.BootService;
import com.javarush.island.nikitin.application.services.EcoSystemService;
import com.javarush.island.nikitin.application.services.PreparationService;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.domain.usecase.IslandService;
import com.javarush.island.nikitin.presentation.api.View;

// TODO запуск приложения. Конструктор принимает объект вида , с которым будет работат - консоль или SWING
public class AppRunner {
    private final View view;

    public AppRunner(View view) {
        this.view = view;
    }

    public void start() {
        var bootService = new BootService();
        var islandCreator = new IslandService();
        var ecoSystem = new EcoSystem();
        var island = new Island(DefaultSettings.ROWS, DefaultSettings.COLUMNS);
        var navigator = new Navigator();

        var preparationService = new PreparationService(bootService, islandCreator, ecoSystem, island, navigator);
        var ecoSystemService = new EcoSystemService(ecoSystem, view);

        preparationService.make();
        ecoSystemService.simulateDay();
    }
}
