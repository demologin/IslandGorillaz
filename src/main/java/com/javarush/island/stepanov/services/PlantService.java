package com.javarush.island.stepanov.services;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.oganism.interfaces.Eatable;
import com.javarush.island.stepanov.entity.oganism.interfaces.Reproduceble;

import java.util.List;


public class PlantService extends Organism implements Eatable,Reproduceble,Cloneable {

    @Override
    public boolean eat(Cell cell) {
        double setWeight = getMaxWeight();
        setWeight(setWeight);
        return true;
    }

    @Override
    public void reproduce(Cell cell) {
        if (weight>=maxWeight){
            List<Organism> list = cell.getResidentMap().get(name);
            double birthWeightLossRate = Setting.get().getBirthWeightLossRate();
            double newWeight = weight*birthWeightLossRate;
            setWeight(newWeight);
            PlantService newplant = clone();
            list.add(newplant);
        }
    }

    @Override
    public PlantService clone() {
        return (PlantService) super.clone();
    }
}
