package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Овечка", icon = "\uD83D\uDC11", maxWeight = 70, maxCountInCell = 140,
        flockSize = 35, maxSpeed = 3, maxFood = 15)
public class Sheep extends Herbivore {
    public Sheep(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
