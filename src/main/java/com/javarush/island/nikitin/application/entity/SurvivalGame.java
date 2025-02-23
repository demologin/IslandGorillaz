package com.javarush.island.nikitin.application.entity;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.navigation.Navigator;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.domain.usecase.IslandService;
import com.javarush.island.nikitin.domain.usecase.PopulationService;
import com.javarush.island.nikitin.domain.usecase.TaskService;
import lombok.Getter;

@Getter
public class SurvivalGame {
    private final Island island;
    private final TaskService taskService;
    private final Navigator navigator;
    private final PopulationService populationService;
    private final EcoSystem ecoSystem;
    private final IslandService islandService;

    public SurvivalGame(Settings settings) {
        this.island = new Island(settings.getRows(), settings.getColumns());
        this.taskService = new TaskService();
        this.navigator = new Navigator();
        this.populationService = new PopulationService();
        this.ecoSystem = new EcoSystem(taskService, island, settings.getStartDate());
        this.islandService = new IslandService(island, populationService, settings.getPercentFillingLocation());
    }
}
