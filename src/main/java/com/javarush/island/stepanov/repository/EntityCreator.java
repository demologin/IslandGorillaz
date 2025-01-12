package com.javarush.island.stepanov.repository;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.services.PlantService;
import com.javarush.island.stepanov.util.Rnd;

import java.util.*;

import static com.javarush.island.stepanov.constants.Constants.MAX_PERCENT;
import static com.javarush.island.stepanov.constants.Constants.MIN_PERCENT;

public class EntityCreator {
    private static final int OCCUPANCY_RATE = Setting.get().getOccupancyRate();

    public Cell createRandomCell() {
        Cell cell = new Cell();
        HashMap residentMap = cell.getResidentMap();
        int occupancyRate = Setting.get().getOccupancyRate();
        putRandomAnimals(residentMap);
        putRandomPlants(residentMap);
        return cell;
    }

    private static void putRandomAnimals(HashMap residentMap) {
        AnimalService[] animalprototypes = Setting.PROTOTYPES_ANIMALS;
        for (AnimalService prototype : animalprototypes) {
            List <Organism> listResident = new ArrayList<>();
            int occupancyNumber = getOccupancyNumber(prototype);
            for (int i = 0; i < occupancyNumber; i++) {
                AnimalService newAnimal = prototype.clone();
                listResident.add(newAnimal);
            }
            residentMap.put(prototype.getName(), listResident);
        }
    }

    private static void putRandomPlants(HashMap residentMap) {
        PlantService[] plantprototypes = Setting.PROTOTYPES_PLANTS;
        for (PlantService prototype : plantprototypes) {
            int occupancyNumber = getOccupancyNumber(prototype);
            List<Organism> listResident = new ArrayList<>();
            for (int i = 0; i < occupancyNumber; i++) {
                PlantService newPlant = prototype.clone();
                listResident.add(newPlant);
            }
            residentMap.put(prototype.getName(), listResident);
        }
    }

    private static int getOccupancyNumber(Organism prototype) {
        int maxCountInCell = prototype.getMaxCountInCell();
        int flockSize = prototype.getFlockSize();
        int maxOfFlocks = maxCountInCell * OCCUPANCY_RATE / (flockSize*MAX_PERCENT);
        int minOfFlocks = Setting.get().getMinOfFlocks();

        int randomizedOccupancyNumber = Rnd.random(minOfFlocks, maxOfFlocks);
        return randomizedOccupancyNumber;
    }

}

