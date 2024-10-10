package com.javarush.island.siberia.simulation;

import com.javarush.island.siberia.config.OrganismSettings;
import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.map.Island;
import com.javarush.island.siberia.entity.organism.Organism;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The Simulation class controls the execution of the island simulation.
 * It manages the island's lifecycle, processes each simulation step,
 * and integrates with the graphical user interface.
 */

public class Simulation {
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private boolean isRunning = false;

    private int birthsCount = 0;

    /**
     * Creates a new Simulation object.
     *
     * @param island The island object representing the map and organisms.
     */

    public Simulation(Island island) {
        this.island = island;
    }

    /**
     * Starts the simulation, scheduling steps at a fixed rate defined
     * by the simulation step duration from the settings.
     */

    public void start() {
        if (!isRunning) {
            isRunning = true;
            long stepDuration = Settings.getInstance().getSimulationStepDuration();
            scheduler.scheduleAtFixedRate(this::simulateStep, 0, stepDuration, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Stops the simulation and shuts down the scheduler and island's executor service.
     */
    public void stop() {
        isRunning = false;
        scheduler.shutdown();
        island.getExecutorService().shutdown();
    }

    /**
     * Processes a single step of the simulation.
     * Each step includes processing all locations on the island,
     * performing organism actions (move, eat, reproduce, etc.),
     */

    private void simulateStep() {
        if (!isRunning) {
            return;
        }

        int previousOrganismCount = getTotalOrganismCount();
        birthsCount = 0;

        island.processAllLocations();

        Map<String, Integer> statistics = collectStatistics();
        printStatistics(statistics);

        int currentOrganismCount = getTotalOrganismCount();
        birthsCount = currentOrganismCount - previousOrganismCount;

        printBirthStatistics();

        printIsland();
    }

    public Map<String, Integer> collectStatistics() {
        Map<String, Integer> statistics = new HashMap<>();

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                var location = island.getLocation(x, y);
                var organisms = location.getOrganisms();

                for (Organism organism : organisms) {
                    String species = organism.getClass().getSimpleName();
                    statistics.merge(species, 1, Integer::sum);
                }
            }
        }
        return statistics;
    }

    /**
     * Prints the statistics of the simulation for each species,
     * displaying their icon and population count.
     *
     * @param statistics A map of species names and their respective population counts.
     */

    public void printStatistics(Map<String, Integer> statistics) {
        System.out.println("\nStatistics by species: ");
        statistics.forEach((species, count) -> {
            String icon = getIconBySpecies(species);
            System.out.printf("%s %s: %d%n", icon, species, count);
        });
    }

    /**
     * Retrieves the icon for a given species from the settings.
     *
     * @param species The species name (e.g., "Wolf", "Rabbit").
     * @return The icon (emoji) associated with the species.
     */

    private String getIconBySpecies(String species) {
        OrganismSettings settings = Settings.getInstance().getOrganismSettings(species);
        return settings.getIcon();
    }

    /**
     * Prints a visual representation of the island, displaying organisms
     * with their respective icons in each location.
     */

    public void printIsland() {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                var location = island.getLocation(x, y);
                var organisms = location.getOrganisms();

                if (organisms.isEmpty()) {
                    System.out.print("\u2620\ufe0f ");
                } else {
                    Map<String, Integer> speciesCount = new HashMap<>();
                    for (Organism organism : organisms) {
                        String icon = organism.getIcon();
                        speciesCount.merge(icon, 1, Integer::sum);
                    }
                    String dominantSpeciesIcon = speciesCount.entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .get()
                            .getKey();
                    System.out.print("" + dominantSpeciesIcon + " ");
                }
            }
            System.out.println();
        }
    }

    private int getTotalOrganismCount() {
        int count = 0;
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                count += island.getLocation(x, y).getOrganisms().size();
            }
        }
        return count;
    }

    private void printBirthStatistics() {
        System.out.printf("Births or dead if minus: %d%n", birthsCount);
    }

}