package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.domain.entity.map.Island;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import com.javarush.island.nikitin.domain.usecase.IslandService;
import com.javarush.island.nikitin.domain.usecase.PopulationService;

public class PreparationService {
    private final IslandService islandService;
    private final Navigator navigator;
    private final BootService bootService;
    private final Settings settings;
    private final PopulationService populationService;


    public PreparationService(Settings settings, Island island) {
        this.settings = settings;

        this.populationService = new PopulationService();
        this.islandService = new IslandService(island, populationService);

        this.navigator = new Navigator();

        var prototypeAssembler = new PrototypeService(settings);
        this.bootService = new BootService(prototypeAssembler);
    }

    public void make() {
        RegistryProto registryProto = makeStartConfigProto();
        initializePopulationService(registryProto);
        initializeIslandService(settings.getPercentFillingLocation());
        initializeNavigator();
    }

    private void initializeIslandService(double percentFillingLocation) {
        islandService.fillIslandWithInhabitedLocations(percentFillingLocation);
    }

    private RegistryProto makeStartConfigProto() {
        bootService.loadComponents();
        bootService.setAttributesIntoProto(navigator, settings.getStartDate());
        return bootService.fillRepository();
    }

    private void initializePopulationService(RegistryProto registryProto) {
        populationService.setRegistryProto(registryProto);
    }

    private void initializeNavigator() {
        navigator.initializeIslandMap(islandService.getInhabitedLocations());
    }
}
