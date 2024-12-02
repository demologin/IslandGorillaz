package com.javarush.island.arsinov;

import com.javarush.island.arsinov.config.GameConfig;
import com.javarush.island.arsinov.creator.AnimalCreator;
import com.javarush.island.arsinov.model.Island;
import com.javarush.island.arsinov.model.animals.AnimalType;
import com.javarush.island.arsinov.view.View;

import java.util.EnumSet;

import static com.javarush.island.arsinov.config.GameConfig.*;

public class Runner {
    public static void main(String[] args) {

        Island island = new Island(ISLAND_WIDTH,ISLAND_HEIGHT,INITIAL_PLANTS);
        AnimalCreator creator = new AnimalCreator();

        EnumSet<AnimalType> animalTypes = EnumSet.allOf(AnimalType.class);

        creator.populateIsland(island, animalTypes,MAX_ANIMALS_PER_CELL);

        View view = new View();

        for (int i = 0; i < GameConfig.TOTAL_ITERATIONS; i++) {
            System.out.println("Iteration " + (i + 1));
            island.step();
            view.displayIslandState(island);
            view.displayAnimalStatistics(island);
            view.displayPlantStatics(island);
        }
        island.shutdown();
    }
}
