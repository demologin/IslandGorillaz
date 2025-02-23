package com.javarush.island.myasnikov.entity.herbivore;

import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;

public class Deer extends Herbivore {

    public Deer(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }

}
