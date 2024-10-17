package com.javarush.island.siberia.entity.map;

import com.javarush.island.siberia.config.Settings;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Location class represents a single cell on the island.
 * Each location holds a list of organisms and manages the interactions between them.
 * Locations are processed concurrently by threads during each simulation step.
 */

@Getter
public class Location {
    private final int x;
    private final int y;
    private final Island island;
    private final ReentrantLock lock = new ReentrantLock();
    private final List<Organism> organisms = new CopyOnWriteArrayList<>();
    private final Map<String, Integer> organismCounts = new ConcurrentHashMap<>();

    /**
     * Constructs a Location object.
     *
     * @param x      The x-coordinate of the location.
     * @param y      The y-coordinate of the location.
     * @param island The Island to which this location belongs.
     */

    public Location(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
    }

    /**
     * Checks if an organism can be added to this location based on the maximum capacity.
     *
     * @param organism The organism to be added.
     * @return True if the organism can be added, otherwise false.
     */

    public boolean canAddOrganism(Organism organism) {
        String species = organism.getClass().getSimpleName();
        int maxCount = Settings.getInstance().getOrganismSettings(species).getMaxCountPerCell();
        int currentCount = organismCounts.getOrDefault(species, 0);
        return currentCount < maxCount;
    }

    /**
     * Adds an organism to this location and updates the organism count.
     *
     * @param organism The organism to be added.
     */

    public void addOrganism(Organism organism) {
        lock.lock();
        try {
            if (canAddOrganism(organism)) {
                organisms.add(organism);
                String species = organism.getClass().getSimpleName();
                organismCounts.merge(species, 1, Integer::sum);
                organism.setLocation(this);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes an organism from this location and updates the organism count.
     *
     * @param organism The organism to be removed.
     */

    public void removeOrganism(Organism organism) {
        lock.lock();
        try {
            organisms.remove(organism);
            String species = organism.getClass().getSimpleName();
            organismCounts.merge(species, -1, Integer::sum);
            if (organismCounts.get(species) <= 0) {
                organismCounts.remove(species);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves the count of organisms of a specific species in this location.
     *
     * @param species The species name to count.
     * @return The number of organisms of the specified species in this location.
     */

    public int getOrganismCount(String species) {
        return organismCounts.getOrDefault(species, 0);
    }

    /**
     * Returns a list of adjacent locations (up, down, left, right) for this location.
     *
     * @return A list of neighboring Location objects.
     */

    public List<Location> getAdjacentLocations() {
        List<Location> adjacentLocations = new ArrayList<>();
        int x = this.getX();
        int y = this.getY();

        int width = island.getWidth();
        int height = island.getHeight();

        int[][] directions = {
                {-1, 0},    //left
                {1, 0},     //right
                {0, -1},    //up
                {0, 1}      //down
        };

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                Location adjacentLocation = island.getLocation(newX, newY);
                adjacentLocations.add(adjacentLocation);
            }
        }
        return adjacentLocations;
    }

    /**
     * Processes all organisms in this location, allowing them to perform actions
     * such as moving, eating, reproducing, and increasing hunger. This method
     * is called during each simulation step by multiple threads.
     */

    public void processOrganisms() {
        lock.lock();
        try {
            for (Organism organism : new ArrayList<>(organisms)) {
                if (organism.isAlive()) {
                    if (organism instanceof Animal) {
                        Animal animal = (Animal) organism;
                        animal.move();
                        animal.eat();
                        animal.increaseHunger();
                    }
                    organism.reproduce();
                }
            }
        } finally {
            lock.unlock();
        }
    }

}