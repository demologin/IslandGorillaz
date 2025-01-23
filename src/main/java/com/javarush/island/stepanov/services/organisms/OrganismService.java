package com.javarush.island.stepanov.services.organisms;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.entity.map.Cell;

import java.util.HashMap;
import java.util.List;

import static com.javarush.island.stepanov.constants.Constants.DIE_WEIGHT;
import static com.javarush.island.stepanov.constants.Constants.FIRST_NUMBRER;

public abstract class OrganismService extends Organism {
    @Override
    public Organism clone() {
        return (OrganismService) super.clone();
    }

    public boolean starve(Cell cell) {
        double starveRate = Setting.get().getStarveRate();
        double starveWeight = starveRate * maxWeight;
        double newRate = weight-starveWeight;
        List<Organism> list = cell.getResidentMap().get(name);
        if (newRate <= DIE_WEIGHT) {
            list.remove(this);
            return true;
        }
        setWeight(newRate);
        return false;
    }

    @Override
    public void reproduce(Cell cell) {
        if (weight >= maxWeight) {
            List<Organism> list = cell.getResidentMap().get(name);
            double birthWeightLossRate = Setting.get().getBirthWeightLossRate();
            double newWeight = weight * birthWeightLossRate;
            setWeight(newWeight);
            Organism newOrganism = clone(); // Используем метод clone()
            list.add(newOrganism);
        }
    }

    public void limitOrganisms(Cell cell) {
        HashMap<String, List<Organism>> residentMap = cell.getResidentMap();
        List<Organism> list = residentMap.get(name);
        int flocksInCell = list.size();
        int maxFlocksInCell = maxCountInCell/flockSize;
        if (flocksInCell > maxFlocksInCell) {
            for (int i = list.size()-1; i > maxFlocksInCell; i--) {
                list.remove(i);
            }
        }
    }
}
