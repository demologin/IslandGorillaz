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

@Getter
public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;
    private final ExecutorService executorService;

    public Island() {
        Settings settings = Settings.getInstance();
        Map<String, Object> islandSettings = (Map<String, Object>) settings.getConfig().get("island");
        this.width = (int) islandSettings.get("width");
        this.height = (int) islandSettings.get("height");
        this.locations = new Location[width][height];
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        initializeLocations();
    }

    public void initializeLocations() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y] = new Location(x, y, this);
            }
        }
    }

    public Location getLocation(int x, int y) {
        //return locations[x][y];
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return locations[x][y];
        } else {
            throw new IndexOutOfBoundsException("incorrect coordinate: (" + x + ", " + y + ")");
        }
    }

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

    private Location getRandomLocation() {
        int x = RandomUtils.randomInt(0, width - 1);
        int y = RandomUtils.randomInt(0, height - 1);
        return locations[x][y];
    }

    public void processAllLocations() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = locations[x][y];
                executorService.submit(location::processOrganisms);
            }
        }
    }

}