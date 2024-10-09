package com.javarush.island.siberia.service;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.animals.Animal;
import com.javarush.island.siberia.utils.RandomUtils;

import java.util.List;

public class ReproductionService implements ReproductionBehavior {

    private final Organism organism;

    public ReproductionService(Organism organism) {
        this.organism = organism;
    }

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