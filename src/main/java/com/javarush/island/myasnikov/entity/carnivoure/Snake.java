package com.javarush.island.myasnikov.entity.carnivoure;

import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;

public class Snake extends Carnivore {

    public Snake(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }
}
