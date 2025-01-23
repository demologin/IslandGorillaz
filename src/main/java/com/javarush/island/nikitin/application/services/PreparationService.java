package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.entity.SurvivalGame;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.domain.usecase.IslandService;
import com.javarush.island.nikitin.domain.usecase.PopulationService;

public class PreparationService {
    private final BootService bootService;
    private final Settings settings;
    private final SurvivalGame survivalGame;

    public PreparationService(Settings settings, SurvivalGame survivalGame) {
        this.settings = settings;
        this.survivalGame = survivalGame;
        this.bootService = new BootService(settings);
    }

    public EcoSystem setupEcoSystem() {
        RegistryProto registryProto = makeStartConfigProto();
        initPopulationService(registryProto);
        initIslandService();
        initNavigator();
        return survivalGame.getEcoSystem();
    }

    private RegistryProto makeStartConfigProto() {
        bootService.loadComponents();
        Navigator navigator = survivalGame.getNavigator();
        int startDate = settings.getStartDate();
        bootService.setAttributesIntoProto(navigator, startDate);
        return bootService.fillRepository();
    }

    private void initPopulationService(RegistryProto registryProto) {
        PopulationService populationService = survivalGame.getPopulationService();
        populationService.setRegistryProto(registryProto);
    }

    private void initIslandService() {
        IslandService islandService = survivalGame.getIslandService();
        islandService.fillIslandWithInhabitedLocations();
    }

    private void initNavigator() {
        IslandService islandService = survivalGame.getIslandService();
        Navigator navigator = survivalGame.getNavigator();
        Location[][] inhabitedLocations = islandService.getHasInhabitedLocations();
        navigator.initializeIslandMap(inhabitedLocations);
        navigator.warmUpCache(settings.getStepWarmUpCacheNavigator());
    }
}
