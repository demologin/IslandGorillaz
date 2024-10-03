package com.javarush.island.khmelov.entity.organizm.animals;

import com.javarush.island.khmelov.api.entity.Eating;
import com.javarush.island.khmelov.api.entity.Movable;
import com.javarush.island.khmelov.api.entity.Reproducible;
import com.javarush.island.khmelov.config.Setting;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.entity.organizm.Limit;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.Organisms;

public abstract class Animal extends Organism implements Eating, Reproducible, Movable {

    public Animal(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

    @Override
    public boolean eat(Cell currentCell) {
        //logic
        return false;
    }


    @Override
    public boolean move(Cell startCell) {
        //logic
        return false;
    }

    @Override
    public boolean spawn(Cell cell) {
        //logic
        return false;
    }


}
