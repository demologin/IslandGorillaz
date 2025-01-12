package com.javarush.island.stepanov.config;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.services.PlantService;
import com.javarush.island.stepanov.util.YamalUtil;

import java.util.List;

public class EntityScanner {
    private static final String ANIMALS_YAMAL = "stepanov/animal";
    private static final String PLANTS_YAMAL = "stepanov/plant";

    private EntityScanner() {
    }

    public static Organism[] createPrototypes() {

        List<String> animalPaths = YamalUtil.getYamlFilesFromDirectory(ANIMALS_YAMAL);
        List<String> plantPaths = YamalUtil.getYamlFilesFromDirectory(PLANTS_YAMAL);
        int organismsQuantity = animalPaths.size() + plantPaths.size();
        Organism[] organisms = new Organism[organismsQuantity];
        createAnimals(organisms, animalPaths);
        createPlants(organisms,plantPaths);
        return organisms;
    }

    private static void createAnimals(Organism[] organisms, List<String> animalPaths) {
        for (int i = 0; i < animalPaths.size(); i++) {
            AnimalService animal = new AnimalService();
            YamalUtil.loadFromYaml(animal, animalPaths.get(i));
            organisms[i] = animal;
        }
    }

    private static void createPlants(Organism[] organisms, List<String> plantPaths) {
        int startIndex = organisms.length - plantPaths.size();
        for (int i = 0; i < plantPaths.size(); i++) {
            PlantService plantService = new PlantService();
            YamalUtil.loadFromYaml(plantService, plantPaths.get(i));
            organisms[startIndex + i] = plantService;
        }
    }


}