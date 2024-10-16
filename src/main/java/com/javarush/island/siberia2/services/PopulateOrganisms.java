package com.javarush.island.siberia2.services;

import com.javarush.island.siberia2.config.Settings;
import com.javarush.island.siberia2.entity.animals.Animal;
import com.javarush.island.siberia2.entity.animals.AnimalFactory;
import com.javarush.island.siberia2.entity.map.Cell;
import com.javarush.island.siberia2.entity.map.Island;
import com.javarush.island.siberia2.entity.plants.Plant;
import com.javarush.island.siberia2.entity.plants.PlantFactory;
import com.javarush.island.siberia2.util.RandomUtils;

public class PopulateOrganisms {

    public void populate(Island island, Settings settings) {

        //place animals
        settings.getAnimalsSettings().forEach((name, animalSettings) -> {
            int maxCount = animalSettings.getMaxCountPerCell();
            int totalAnimals = (island.getWidth() * island.getHeight() * maxCount) / 10; //todo настроить коэффициент

            for (int i = 0; i < totalAnimals; i++) {
                int x = RandomUtils.randomInt(0, island.getWidth() - 1);
                int y = RandomUtils.randomInt(0, island.getHeight() - 1);
                Cell cell = island.getCell(x, y);

                if (cell.getAnimals().size() < maxCount) {
                    Animal animal = AnimalFactory.createAnimal(name, animalSettings);
                    animal.setCurrentCell(cell);
                    cell.addAnimal(animal);
                    // для отладки. удалить
                    System.out.println("животное создано: " + name + " в клетке (" + x + ", " + y + ")");

                }
            }
        });

        //place plants
        settings.getPlantsSettings().forEach((name, plantSettings) -> {
            int maxCount = plantSettings.getMaxCountPerCell();
            int totalPlants = (island.getWidth() * island.getHeight() * maxCount) / 5; //todo настроить коэффициент

            for (int i = 0; i < totalPlants; i++) {
                int x = RandomUtils.randomInt(0, island.getWidth() - 1);
                int y = RandomUtils.randomInt(0, island.getHeight() - 1);
                Cell cell = island.getCell(x, y);

                if (cell.getPlants().size() < maxCount) {
                    Plant plant = PlantFactory.createPlant(name, plantSettings);
                    plant.setCurrentCell(cell);
                    cell.addPlant(plant);
                    // для отладки. удалить
                    System.out.println(" растение создано: " + name + " в клетке(" + x + ", " + y + ")");
                }
            }
        });
    }

}