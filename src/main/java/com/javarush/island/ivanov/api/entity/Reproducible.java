package com.javarush.island.ivanov.api.entity;


import com.javarush.island.ivanov.entity.map.Cell;
import com.javarush.island.ivanov.entity.organism.Organism;

@FunctionalInterface
public interface Reproducible {

    <T extends Organism> T reproduce(Cell cell);

}
