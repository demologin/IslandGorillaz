package com.javarush.island.arsinov.creator;

import com.javarush.island.arsinov.model.*;
import com.javarush.island.arsinov.model.animals.*;
import com.javarush.island.arsinov.model.animals.carnivores.*;
import com.javarush.island.arsinov.model.animals.herbivores.*;
import com.javarush.island.arsinov.model.plants.Plant;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalCreator {

    public void populateIsland(Island island, EnumSet<AnimalType> animalTypes, int maxAnimalsPerCell) {
        ArrayList<AnimalType> animalTypesList = new ArrayList<>(animalTypes);
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Cell cell = island.getCell(i, j);

                for (int n = 0; n < 4; n++) {
                    cell.addPlant(new Plant(cell.getX(), cell.getY()));
                }

                int numAnimals = ThreadLocalRandom.current().nextInt(1, maxAnimalsPerCell + 1);

                for (int k = 0; k < numAnimals; k++) {
                    AnimalType randomAnimalType = animalTypesList.get(ThreadLocalRandom.current().nextInt(animalTypesList.size()));
                    Animal animal = createAnimal(randomAnimalType, i, j);
                    cell.getAnimals().add(animal);
                }
            }
        }
    }

    public Animal createAnimal(AnimalType type, int x, int y) {
        AnimalAttributes attributes = AnimalParameters.PARAMETERS.get(type);
        if (attributes == null) {
            throw new IllegalArgumentException("Invalid animal type: " + type);
        }

        return switch (type) {
            case WOLF -> new Wolf(x, y, attributes);
            case BOA -> new Boa(x, y, attributes);
            case FOX -> new Fox(x, y, attributes);
            case GRIZZLY -> new Grizzly(x, y, attributes);
            case EAGLE -> new Eagle(x, y, attributes);
            case HORSE -> new Horse(x, y, attributes);
            case DEER -> new Deer(x, y, attributes);
            case RABBIT -> new Rabbit(x, y, attributes);
            case MOUSE -> new Mouse(x, y, attributes);
            case GOAT -> new Goat(x, y, attributes);
            case SHEEP -> new Sheep(x, y, attributes);
            case WILD_BOAR -> new WildBoar(x, y, attributes);
            case BUFFALO -> new Buffalo(x, y, attributes);
            case DUCK -> new Duck(x, y, attributes);
            default -> throw new IllegalArgumentException("Invalid animal type");
        };
    }
}
