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

public class Simulation {
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private boolean isRunning = false;

    public Simulation(Island island) {
        this.island = island;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            long stepDuration = Settings.getInstance().getSimulationStepDuration();
            scheduler.scheduleAtFixedRate(this::simulateStep, 0, stepDuration, TimeUnit.MILLISECONDS);
        }
    }

    public void stop() {
        isRunning = false;
        scheduler.shutdown();
        island.getExecutorService().shutdown();
    }

    private void simulateStep() {
        if (!isRunning) {
            return;
        }
        island.processAllLocations();

        Map<String, Integer> statistics = collectStatistics();
        printStatistics(statistics);

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

    public void printStatistics(Map<String, Integer> statistics) {
        System.out.println("\nStatistics by species: ");
        statistics.forEach((species, count) -> {
            String icon = getIconBySpecies(species);
            System.out.printf("%s %s: %d%n", icon, species, count);
        });
    }

    private String getIconBySpecies(String species) {
        OrganismSettings settings = Settings.getInstance().getOrganismSettings(species);
        return settings.getIcon();
    }

    public void printIsland() {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                var location = island.getLocation(x, y);
                var organisms = location.getOrganisms();

                if (organisms.isEmpty()) {
                    System.out.print("\u2620\ufe0f ");
                } else {
                    Map<String, Integer> speciesCount = new HashMap<>();
                    for (Organism organism :organisms) {
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

}