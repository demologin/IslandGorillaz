package com.javarush.island.myasnikov.entity.carnivoure;

import com.javarush.island.myasnikov.entity.Animal;
import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;


public abstract class Carnivore extends Animal {

    public Carnivore(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }
}
