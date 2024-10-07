package com.javarush.island.siberia.entity.organism;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.carnivores.Wolf;

public class OrganismFactory {
    public static Organism createOrganism(String species, Location location) {
        switch (species) {
            case "Wolf" : return new Wolf(location);

            default : throw new IllegalArgumentException("Unknown species: " + species);
        }
    }
}