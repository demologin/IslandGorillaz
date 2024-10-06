package com.javarush.island.gerasimov.entity.map;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import lombok.Data;

import java.util.Set;

@Data
public class Cell {
    private Set<Organism> organisms;
}
