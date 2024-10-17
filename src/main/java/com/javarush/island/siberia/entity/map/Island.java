package com.javarush.island.siberia.entity.map;

import com.javarush.island.siberia.config.OrganismSettings;
import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.service.OrganismFactory;
import com.javarush.island.siberia.utils.RandomUtils;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Island class represents the entire simulation environment,
 * consisting of a 2D grid of Location objects.
 * Each Location contains various organisms, and the Island class is responsible for managing
 * the simulation's state, initializing locations, and processing organism actions across the island.
 */

@Getter
public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;
    private final ExecutorService executorService;

    /**
     * Constructs an Island object and initializes the locations on the grid.
     */

    public Island() {
        Settings settings = Settings.getInstance();
        Map<String, Object> islandSettings = (Map<String, Object>) settings.getConfig().get("island");
        this.width = (int) islandSettings.get("width");
        this.height = (int) islandSettings.get("height");
        this.locations = new Location[width][height];
        this.executorService = Executors.newFixedThreadPool(100);
        //this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        initializeLocations();
    }

    /**
     * Initializes all locations on the island.
     * Each location is a cell in the grid that can hold organisms.
     */

    public void initializeLocations() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y] = new Location(x, y, this);
            }
        }
    }

    /**
     * Retrieves a specific location by its coordinates.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     * @return The Location object at the specified coordinates.
     * @throws IndexOutOfBoundsException If the coordinates are out of bounds.
     */

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return locations[x][y];
        } else {
            throw new IndexOutOfBoundsException("incorrect coordinate: (" + x + ", " + y + ")");
        }
    }

    /**
     * Populates the island with organisms based on the settings.
     * Organisms are randomly distributed across the grid.
     */
    public void populateIsland() {
        Settings settings = Settings.getInstance();
        Map<String, OrganismSettings> organismsSettings = settings.getAllOrganismsSettings();

        for (Map.Entry<String, OrganismSettings> entry :organismsSettings.entrySet()) {
            String species = entry.getKey();
            OrganismSettings organismSettings = entry.getValue();
            int initialCount = organismSettings.getInitialCount();

            for (int i = 0; i < initialCount; i++) {
                Location randomLocation = getRandomLocation();
                Organism organism = OrganismFactory.createOrganism(species, randomLocation);
                randomLocation.addOrganism(organism);
            }
        }
    }

    /**
     * Returns a random location on the island.
     *
     * @return A randomly selected Location object.
     */

    private Location getRandomLocation() {
        int x = RandomUtils.randomInt(0, width - 1);
        int y = RandomUtils.randomInt(0, height - 1);
        return locations[x][y];
    }

    /**
     * Processes all locations on the island using a thread pool.
     * Each location is processed concurrently.
     */

    public void processAllLocations() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = locations[x][y];
                executorService.submit(location::processOrganisms);
            }
        }
    }

}