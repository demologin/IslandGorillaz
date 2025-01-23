package com.javarush.island.stepanov.services.organisms;

import com.javarush.island.stepanov.entity.Organism;
import com.javarush.island.stepanov.entity.map.Cell;


public class PlantService extends OrganismService {

    @Override
    public boolean eat(Cell cell) {
        double setWeight = getMaxWeight();
        setWeight(setWeight);
        return true;
    }

    @Override
    public PlantService clone() {
        return (PlantService) super.clone();
    }
}
