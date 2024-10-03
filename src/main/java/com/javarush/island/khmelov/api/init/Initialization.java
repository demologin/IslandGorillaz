package com.javarush.island.khmelov.api.init;

import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Organism;

import java.util.List;

public interface Initialization {

    Cell createRandomCell(boolean empty);

    List<Organism> getAllPrototypes();

}
