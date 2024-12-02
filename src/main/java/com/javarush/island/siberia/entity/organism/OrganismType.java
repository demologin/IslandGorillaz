package com.javarush.island.siberia.entity.organism;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.carnivores.*;
import com.javarush.island.siberia.entity.organism.animals.herbivores.*;
import com.javarush.island.siberia.entity.organism.animals.plants.Plant;
import lombok.Getter;

import java.util.function.Function;

/**
 * The OrganismType enum defines the various species of organisms
 * (animals and plants) that exist on the island. Each enum constant
 * corresponds to a specific species and provides a factory method for
 * creating instances of that species.
 */

public enum OrganismType {
    WOLF("Wolf", Wolf::new),
    BOA("Boa", Boa::new),
    FOX("Fox", Fox::new),
    BEAR("Bear", Bear::new),
    EAGLE("Eagle", Eagle::new),
    HORSE("Horse", Horse::new),
    DEER("Deer", Deer::new),
    RABBIT("Rabbit", Rabbit::new),
    MOUSE("Mouse", Mouse::new),
    GOAT("Goat", Goat::new),
    SHEEP("Sheep", Sheep::new),
    BOAR("Boar", Boar::new),
    BUFFALO("Buffalo", Buffalo::new),
    DUCK("Duck", Duck::new),
    CATERPILLAR("Caterpillar", Caterpillar::new),
    PLANT("Plant", Plant::new);

    @Getter
    private final String speciesName;
    private final Function<Location, Organism> constructor;

    /**
     * Constructs an OrganismType enum constant for a given species name and
     * a function that serves as a factory for creating instances of that species.
     *
     * @param speciesName The name of the species (e.g., "Wolf", "Rabbit").
     * @param constructor A function that creates an organism of this species given a location.
     */

    OrganismType(String speciesName, Function<Location, Organism> constructor) {
        this.speciesName = speciesName;
        this.constructor = constructor;
    }

    /**
     * Creates a new organism of this type at the specified location.
     *
     * @param location The location where the organism is created.
     * @return A new Organism instance for this species.
     */

    public Organism createOrganism(Location location) {
        return constructor.apply(location);
    }

    /**
     * Looks up an OrganismType by the species name. If the species name does not
     * match any known organism type, an exception is thrown.
     *
     * @param speciesName The name of the species (e.g., "Wolf", "Rabbit").
     * @return The OrganismType corresponding to the given species name.
     * @throws IllegalArgumentException If the species name is not recognized.
     */

    public static OrganismType fromString(String speciesName) {
        for (OrganismType type : OrganismType.values()) {
            if (type.getSpeciesName().equals(speciesName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown organism type: " + speciesName);
    }

}