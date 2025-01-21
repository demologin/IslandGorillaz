package com.javarush.island.stepanov.config;

import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.services.PlantService;
import com.javarush.island.stepanov.util.YamalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityScanner {
    private static final String ANIMALS_YAMAL = "stepanov/animal";
    private static final String PLANTS_YAMAL = "stepanov/plant";

    private EntityScanner() {
    }

    protected static Map<String,String> createOrganismsViewMap(AnimalService[] animalServices, PlantService[] plantServices) {
        Map<String,String> organismsViewMap = new HashMap<>();
        for (AnimalService animalService : animalServices) {
            String animalName = animalService.getName();
            String animalView = animalService.getIcon();
            organismsViewMap.put(animalName,animalView);
        }
        for (PlantService plantService : plantServices) {
            String plantName = plantService.getName();
            String plantView = plantService.getIcon();
            organismsViewMap.put(plantName,plantView);
        }
        return organismsViewMap;
    }

    protected static AnimalService[] createAnimalsPrototypes() {
        List<String> animalPaths = YamalUtil.getYamlFilesFromDirectory(ANIMALS_YAMAL);
        AnimalService[] animalPrototypes= new AnimalService[animalPaths.size()];
        for (int i = 0; i < animalPaths.size(); i++) {
            AnimalService animal = new AnimalService();
            YamalUtil.loadFromYaml(animal, animalPaths.get(i));
            animalPrototypes[i] = animal;
        }
        return animalPrototypes;
    }

    protected static PlantService[] createPlantsPrototypes() {
        List<String> plantPaths = YamalUtil.getYamlFilesFromDirectory(PLANTS_YAMAL);
        PlantService[] plantPrototypes= new PlantService[plantPaths.size()];
        for (int i = 0; i < plantPaths.size(); i++) {
            PlantService plant = new PlantService();
            YamalUtil.loadFromYaml(plant, plantPaths.get(i));
            plantPrototypes[i] = plant;
        }
        return plantPrototypes;
    }

}