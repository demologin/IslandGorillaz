package com.javarush.island.myasnikov.entity.herbivore;

import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;
import lombok.Getter;

@Getter
public class Sheep extends Herbivore {

    public Sheep(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }
}