package com.javarush.island.siberia.service;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.Organism;
import com.javarush.island.siberia.entity.organism.OrganismType;


public class OrganismFactory {
    public static Organism createOrganism(String species, Location location) {
        OrganismType type = OrganismType.fromString(species);
        return type.createOrganism(location);
    }
}