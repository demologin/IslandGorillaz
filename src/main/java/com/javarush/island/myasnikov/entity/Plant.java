package com.javarush.island.myasnikov.entity;

import com.javarush.island.myasnikov.api.CanGrow;
import com.javarush.island.myasnikov.entity.organism.Organism;
import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;
import lombok.Getter;

@Getter
public class Plant extends Organism implements CanGrow {

    public Plant(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }

    public void grow() {
        this.addWeight(getLimit().getMaxWeight() * 0.10);
    }

    public void action() {
        grow();
    }
}

