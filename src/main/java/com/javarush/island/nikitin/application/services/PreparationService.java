package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.domain.usecase.IslandService;

public class PreparationService {
    private final BootService bootService;
    private final IslandService islandService;
    private final EcoSystem ecoSystem;
    private final Island island;
    private final Navigator navigator;

    public PreparationService(BootService bootService, IslandService islandService, EcoSystem ecoSystem, Island island, Navigator navigator) {
        this.ecoSystem = ecoSystem;
        this.islandService = islandService;
        this.bootService = bootService;
        this.island = island;
        this.navigator = navigator;
    }

    public void make() {
        bootService.load();
        bootService.installNavigatorInProto(navigator);
        bootService.fillRepository();

        double percentFillingLocation = 0.1d;

        islandService.setIsland(island);
        islandService.fillIsland(bootService.getRepository(), percentFillingLocation);
        islandService.setupNavigation(navigator);
        navigator.initialize();

        ecoSystem.setIsland(island);
    }

}
