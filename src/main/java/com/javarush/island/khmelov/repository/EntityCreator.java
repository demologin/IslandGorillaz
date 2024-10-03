package com.javarush.island.khmelov.repository;

import com.javarush.island.khmelov.api.init.Initialization;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Organism;

import java.util.Arrays;
import java.util.List;

public class EntityCreator implements Initialization {

    //fixed some constants or get from config
    public static final int PERCENT_FILL = 33;
    public static final int PERCENT_PROBABLY_BORN = 50;

    @Override
    public Cell createRandomCell(boolean empty) {
        Cell cell = new Cell();
        //logic create and fill
        return cell;
    }

    @Override
    public List<Organism> getAllPrototypes() {
        return Arrays.asList(Setting.PROTOTYPES);
    }

    @Override
    public String toString() {
        return "EntityFactory{" +
                "prototypes=" + Arrays.toString(Setting.PROTOTYPES) +
                '}';
    }
}
