package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.services.PopulateOrganisms;
import com.javarush.island.siberia2.util.RandomUtils;

public class OrganismPopulation {

    private final PopulateOrganisms populateOrganisms;

    public OrganismPopulation(PopulateOrganisms populateOrganisms) {
        this.populateOrganisms = populateOrganisms;
    }

    public void populateIslandAtStart(Island island, Settings settings) {
        for (String ignored : settings.getAnimalsSettings().keySet()) {
            int x = RandomUtils.randomInt(0, island.getWidth() - 1);
            int y = RandomUtils.randomInt(0, island.getHeight() - 1);
            Cell cell = island.getCell(x, y);

            int animalCount = RandomUtils.randomInt(1, Constants.MAX_ANIMAL_TO_START);
            for (int i = 0; i < animalCount; i++) {
                populateOrganisms.addSingleAnimal(cell, settings);
            }
        }

        for (int i = 0; i < Constants.STARTING_CELLS_WITH_FOOD; i++) {
            int x = RandomUtils.randomInt(0, island.getWidth() - 1);
            int y = RandomUtils.randomInt(0, island.getHeight() - 1);
            Cell cell = island.getCell(x, y);
            populateOrganisms.addSinglePlant(cell, settings);
        }
    }
}