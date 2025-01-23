package com.javarush.island.nikitin.application.controllers;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.entity.DataContainer;
import com.javarush.island.nikitin.application.services.DataService;
import com.javarush.island.nikitin.domain.usecase.EcoSystem;
import com.javarush.island.nikitin.presentation.controllers.View;

/**
 * Controller class that manages the interaction between the view and the data service.
 */

public class AppController {
    private final View view;
    private final DataService dataService;

    public AppController(View view) {
        this.view = view;
        this.dataService = new DataService();
    }

    public void notifyView(EcoSystem ecoSystem, Settings settings) {
        DataContainer data = dataService.getData(ecoSystem, settings);
        view.displayData(data);
    }
}
