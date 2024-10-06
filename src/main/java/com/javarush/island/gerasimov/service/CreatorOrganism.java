package com.javarush.island.gerasimov.service;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import com.javarush.island.gerasimov.entity.creatures.grass.Grass;
import com.javarush.island.gerasimov.entity.creatures.herbivores.Horse;
import com.javarush.island.gerasimov.entity.creatures.predators.Wolf;
import java.util.*;

public class CreatorOrganism {

    private final List<Set<Organism>> organismSets = new ArrayList<>();

    public Organism createOrganism() {
        int random = Math.abs(new Random().nextInt(3));
        return switch (random) {
            case 0 -> new Horse();
            case 1 -> new Wolf();
            case 2 -> new Grass();
            default -> throw new AssertionError();
        };
    }

    public void initialisation()  {
        for (int j = 0; j < 4; j++) {
            Set<Organism> organisms = new HashSet<>();
            organisms.add(createOrganism());
            organisms.add(createOrganism());
            organisms.add(createOrganism());
            organismSets.add(organisms);
        }
    }

    public List<Set<Organism>> getOrganismList() {
        return organismSets;
    }
}