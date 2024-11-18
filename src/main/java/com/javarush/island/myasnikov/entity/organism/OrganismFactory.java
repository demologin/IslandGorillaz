package com.javarush.island.myasnikov.entity.organism;


import com.javarush.island.myasnikov.entity.Plant;
import com.javarush.island.myasnikov.entity.carnivoure.*;
import com.javarush.island.myasnikov.entity.herbivore.*;
import com.javarush.island.myasnikov.utility.OrganismTypes;

import static com.javarush.island.myasnikov.config.Params.*;
import static com.javarush.island.myasnikov.utility.RandomNumberGenerator.getRandomInt;


public class OrganismFactory {

    public Organism createOrganism(OrganismTypes type) {

        Organism organism;

        switch (type) {
            case Wolf -> organism = new Wolf(type,WOLF_ICON,0,WOLF_LIMIT);
            case Snake -> organism = new Snake(type,SNAKE_ICON,0,SNAKE_LIMIT);
            case Fox -> organism = new Fox(type,FOX_ICON,0,FOX_LIMIT);
            case Bear -> organism = new Bear(type,BEAR_ICON,0,BEAR_LIMIT);
            case Eagle -> organism = new Eagle(type,EAGLE_ICON,0,EAGLE_LIMIT);
            case Horse -> organism = new Horse(type,HORSE_ICON,0,HORSE_LIMIT);
            case Deer -> organism = new Deer(type,DEER_ICON,0,DEER_LIMIT);
            case Rabbit -> organism = new Rabbit(type,RABBIT_ICON,0,RABBIT_LIMIT);
            case Mouse -> organism = new Mouse(type,MOUSE_ICON,0,MOUSE_LIMIT);
            case Goat -> organism = new Goat(type,GOAT_ICON,0,GOAT_LIMIT);
            case Sheep -> organism = new Sheep(type,SHEEP_ICON,0,SHEEP_LIMIT);
            case Boar -> organism = new Boar(type,BOAR_ICON,0,BOAR_LIMIT);
            case Buffalo -> organism = new Buffalo(type,BUFFALO_ICON,0,BUFFALO_LIMIT);
            case Duck -> organism = new Duck(type,DUCK_ICON,0,DUCK_LIMIT);
            case Caterpillar -> organism = new Caterpillar(type,CATERPILLAR_ICON,0.01,CATERPILLAR_LIMIT);
            case Plant -> organism = new Plant(type,PLANT_ICON,1,PLANT_LIMIT);
            default -> throw new IllegalArgumentException("Unsupported organism type: " + type);
        }
        int maxWeight = (int) organism.getLimit().getMaxWeight()/2;
        if (maxWeight > 1)
        {
            double generatedWeight = getRandomInt(1, maxWeight);
            organism.addWeight(generatedWeight);
        }
        return organism;
    }


}
