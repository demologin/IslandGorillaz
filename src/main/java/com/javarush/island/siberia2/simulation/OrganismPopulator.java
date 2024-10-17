package com.javarush.island.siberia2.simulation;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.services.PopulateOrganisms;
import com.javarush.island.siberia2.ui.WindowFrame;
import java.util.concurrent.ThreadLocalRandom;

public class OrganismPopulator {

    private final PopulateOrganisms populateOrganisms;
    private final WindowFrame windowFrame;

    public OrganismPopulator(PopulateOrganisms populateOrganisms, WindowFrame windowFrame) {
        this.populateOrganisms = populateOrganisms;
        this.windowFrame = windowFrame;
    }

    public void addSingleOrganism(Cell cell, Settings settings) {
        if (cell.getAnimals().size() < settings.getAnimalsSettings().size()) {
            populateOrganisms.addSingleAnimal(cell, settings);
        }

        if (cell.getPlants().size() < settings.getPlantsSettings().size()) {
            populateOrganisms.addSinglePlant(cell, settings);
        }
    }

    public void addOrganismRandomly(int width, int height, Settings settings, Island island) {
        int x = ThreadLocalRandom.current().nextInt(width);
        int y = ThreadLocalRandom.current().nextInt(height);
        Cell cell = island.getCell(x, y);
        addSingleOrganism(cell, settings);
    }

}