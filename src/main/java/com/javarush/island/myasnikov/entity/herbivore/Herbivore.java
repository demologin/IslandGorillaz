package com.javarush.island.myasnikov.entity.herbivore;

import com.javarush.island.myasnikov.entity.Animal;
import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;

abstract class Herbivore extends Animal {

    public Herbivore(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }

}
