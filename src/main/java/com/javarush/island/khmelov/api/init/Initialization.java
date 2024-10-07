package com.javarush.island.khmelov.api.init;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Organism;

import java.util.List;

public interface Initialization {

    void fill(Cell cell, double percentProbably);

    List<Organism> getAllPrototypes();

}
