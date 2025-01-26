package com.javarush.island.stepanov.repository;

import com.javarush.island.stepanov.config.Setting;
import com.javarush.island.stepanov.entity.map.Cell;
import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.organisms.AnimalService;
import com.javarush.island.stepanov.services.organisms.PlantService;
import com.javarush.island.stepanov.util.Rnd;

import static com.javarush.island.stepanov.constants.Constants.MAX_PERCENT;

public class EntityCreator {
    private static final int OCCUPANCY_RATE = Setting.get().getOccupancyRate();

    private static void putRandomAnimals(Cell cell) {
        AnimalService[] animalprototypes = Setting.PROTOTYPES_ANIMALS;
        for (AnimalService prototype : animalprototypes) {
            int occupancyNumber = getRandomizedOccupancyNumber(prototype);
            for (int i = 0; i < occupancyNumber; i++) {
                AnimalService newAnimal = prototype.clone();
                setRandomWeight(newAnimal);
                cell.addOrganism(newAnimal);
            }
        }
    }

    private static void putRandomPlants(Cell cell) {
        PlantService[] plantprototypes = Setting.PROTOTYPES_PLANTS;
        for (PlantService prototype : plantprototypes) {
            int occupancyNumber = getRandomizedOccupancyNumber(prototype);
            for (int i = 0; i < occupancyNumber; i++) {
                PlantService newPlant = prototype.clone();
                double plantWeigth = newPlant.getMaxWeight();
                newPlant.setWeight(plantWeigth);
                cell.addOrganism(newPlant);
            }
        }
    }

    private static void setRandomWeight(AnimalService animal) {
        double maxWeight = animal.getMaxWeight();
        double minWeight = Setting.get().getMinWeight();
        double randomWeight = Rnd.random(minWeight, maxWeight);
        animal.setWeight(randomWeight);
    }

    private static int getRandomizedOccupancyNumber(Organism prototype) {
        int maxCountInCell = prototype.getMaxCountInCell();
        int flockSize = prototype.getFlockSize();
        int maxOfFlocks = maxCountInCell * OCCUPANCY_RATE / (flockSize * MAX_PERCENT);
        int minOfFlocks = Setting.get().getMinOfFlocks();
        int randomizedOccupancyNumber = Rnd.random(minOfFlocks, maxOfFlocks);
        return randomizedOccupancyNumber;
    }

    public Cell createRandomCell() {
        Cell cell = new Cell();
        int occupancyRate = Setting.get().getOccupancyRate();
        putRandomAnimals(cell);
        putRandomPlants(cell);
        return cell;
    }

}

