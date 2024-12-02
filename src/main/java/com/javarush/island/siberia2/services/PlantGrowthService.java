package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.config.Constants;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.plants.Plant;
import com.javarush.island.siberia2.util.RandomUtils;

import java.util.List;

public class PlantGrowthService {

    public void grow(Plant plant) {
        plant.setWeight(plant.getWeight() + plant.getSettings().getGrowthRate());
        if (plant.getWeight() > plant.getSettings().getWeight()) {
            plant.setWeight(plant.getSettings().getWeight());
        }
        if (RandomUtils.chance(Constants.PLANT_SPREAD_PROBABILITY)) {
            spread(plant);
        }
    }

    private void spread(Plant plant) {
        Cell currentCell = plant.getCurrentCell();
        List<Cell> adjacentCells = new AdjacentCellService().getAdjacentCells(currentCell);

        for (Cell cell : adjacentCells) {
            if (!cell.isWater() && cell.getPlants().size() < plant.getSettings().getMaxCountPerCell()) {
                try {
                    Plant newPlant = plant.getClass()
                            .getConstructor(plant.getSettings().getClass()).newInstance(plant.getSettings());
                    newPlant.setCurrentCell(cell);
                    cell.addPlant(newPlant);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
