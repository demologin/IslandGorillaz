package com.javarush.island.siberia.service;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.OrganismType;

/**
 * The OrganismFactory class is responsible for creating organisms
 * based on the species name and the given location.
 * It uses the OrganismType enum to instantiate the correct type of organism.
 */

public class OrganismFactory {

    /**
     * Creates a new organism based on the species name and assigns it to a location.
     *
     * @param species  The name of the species (e.g., "Wolf", "Bear").
     * @param location The location where the organism will be placed.
     * @return A new instance of the specified organism.
     * @throws IllegalArgumentException If the species name is unknown.
     */

    public static Organism createOrganism(String species, Location location) {
        OrganismType type = OrganismType.fromString(species);
        return type.createOrganism(location);
    }
}