package com.javarush.island.siberia.entity.organism;

import com.javarush.island.siberia.entity.map.Location;
import com.javarush.island.siberia.entity.organism.animals.carnivores.*;
import com.javarush.island.siberia.entity.organism.animals.herbivores.*;
import com.javarush.island.siberia.entity.organism.animals.plants.Plant;


import java.util.function.Function;

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


    private final String speciesName;
    private final Function<Location, Organism> constructor;

    OrganismType(String speciesName, Function<Location, Organism> constructor) {
        this.speciesName = speciesName;
        this.constructor = constructor;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public Organism createOrganism(Location location) {
        return constructor.apply(location);
    }

    public static OrganismType fromString(String speciesName) {
        for (OrganismType type : OrganismType.values()) {
            if (type.getSpeciesName().equals(speciesName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown organism type: " + speciesName);
    }

}