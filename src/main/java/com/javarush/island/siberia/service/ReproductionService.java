package com.javarush.island.siberia.service;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.utils.RandomUtils;
import java.util.List;

/**
 * The ReproductionService class is responsible for handling the reproduction
 * behavior of organisms, including both animals and plants. It implements
 * the ReproductionBehavior interface to provide functionality for organisms
 * to reproduce within the constraints of their environment and population limits.
 */

public class ReproductionService implements ReproductionBehavior {

    private final Organism organism;

    public ReproductionService(Organism organism) {
        this.organism = organism;
    }

    /**
     * Executes the reproduction process for the organism.
     * It first checks if the organism can reproduce based on conditions such as population limits,
     * the number of same-species organisms in the location, and satiety (for animals).
     * If reproduction is allowed, offspring are generated and placed either in the current location
     * or in an adjacent location.
     */

    @Override
    public void reproduce() {
        if (canReproduce()) {
            int maxOffspring = organism.getOrganismSettings().getMaxOffspring();
            int offspringCount = RandomUtils.randomInt(1, maxOffspring);

            for (int i = 0; i < offspringCount; i++) {
                Organism offspring = organism.clone();

                if (organism.getLocation().canAddOrganism(offspring)) {
                    organism.getLocation().addOrganism(offspring);
                } else {
                    Location targetLocation = findAvaibleNeighboringLocation();
                    if (targetLocation != null && targetLocation.canAddOrganism(offspring)) {
                        offspring.setLocation(targetLocation);
                        targetLocation.addOrganism(offspring);
                    }
                }
            }
        }
    }

    /**
     * Determines if the organism can reproduce based on several conditions:
     * - The organism must be alive.
     * - For animals, there must be at least two organisms of the same species in the location.
     * - Animals must have a sufficient satiety level to reproduce.
     * - The current population of the organism's species in the location must be below the maximum capacity.
     *
     * @return True if the organism can reproduce, otherwise false.
     */

    protected boolean canReproduce() {
        if (!organism.isAlive()) {
            return false;
        }

        if (organism instanceof Animal) {
            long sameSpeciesCount = organism.getLocation().getOrganisms().stream()
                    .filter(o -> o.getClass().equals(organism.getClass()))
                    .count();

            if (sameSpeciesCount < 2) {
                return false;
            }

            Animal animal = (Animal) organism;
            double reproductionSatietyThreshold = organism.getOrganismSettings().getReproductionSatietyThreshold();
            if (animal.getSatiety() < reproductionSatietyThreshold) {
                return false;
            }
        }

        String species = organism.getClass().getSimpleName();
        int maxCountPerCell = organism.getOrganismSettings().getMaxCountPerCell();
        int currentCount = organism.getLocation().getOrganismCount(species);

        return currentCount < maxCountPerCell;
    }

    /**
     * Finds an available neighboring location for placing an offspring.
     * It checks adjacent locations around the current organism's location
     * to see if the new offspring can be added to one of them.
     *
     * @return A neighboring location that can accept the offspring, or null if none are available.
     */

    private Location findAvaibleNeighboringLocation() {
        List<Location> neighboringLocation = organism.getLocation().getAdjacentLocations();
        for (Location location : neighboringLocation) {
            if (location.canAddOrganism(organism)) {
                return location;
            }
        }
        return null;
    }

}