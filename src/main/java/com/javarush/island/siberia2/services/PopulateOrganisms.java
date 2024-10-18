package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.animals.AnimalFactory;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.plants.Plant;
import com.javarush.island.siberia2.entity.plants.PlantFactory;

public class PopulateOrganisms {

    public void addSingleAnimal(Cell cell, Settings settings) {
        settings.getAnimalsSettings().forEach((name, animalSettings) -> {
            if (cell.getAnimals().stream().noneMatch(animal -> animal.getSettings().getName().equals(name))) {
                Animal animal = AnimalFactory.createAnimal(name, animalSettings);
                animal.setCurrentCell(cell);
                cell.addAnimal(animal);
            }
        });
    }

    public void addSinglePlant(Cell cell, Settings settings) {
        settings.getPlantsSettings().forEach((name, plantSettings) -> {
            if (cell.getPlants().stream().noneMatch(plant -> plant.getSettings().getName().equals(name))) {
                Plant plant = PlantFactory.createPlant(name, plantSettings);
                plant.setCurrentCell(cell);
                cell.addPlant(plant);
            }
        });
    }

}