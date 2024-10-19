package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.map.Residents;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.Organisms;
import com.javarush.island.khmelov.util.Rnd;

import java.util.Arrays;
import java.util.List;

public class EntityCreator implements Initialization {

    @Override
    public void fill(Cell cell, double percentProbably) {
        Residents residents = cell.getResidents();
        for (Organism prototype : Setting.PROTOTYPES) {
            String type = prototype.getType();
            boolean fill = Rnd.get(percentProbably);
            if (fill) {
                Organisms organisms = residents.get(type);
                int currentCount = organisms.size();
                int max = prototype.getLimit().getMaxCountInCell() - currentCount;
                int count = Rnd.random(0, max);
                for (int i = 0; i < count; i++) {
                    organisms.add(Organism.clone(prototype));
                }
            }
        }
    }

    @Override
    public List<Organism> getAllPrototypes() {
        return Arrays.asList(Setting.PROTOTYPES);
    }


    @Override
    public String toString() {
        return "EntityFactory{" +
               "prototypes=" + Arrays.toString(Setting.PROTOTYPES) +
               '}';
    }
}
