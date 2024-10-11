package com.javarush.island.khmelov.entity.organizm.animals.predators;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Волк", icon = "\uD83D\uDC3A", maxWeight = 50, maxCountInCell = 30,
        flockSize = 4, maxSpeed = 3, maxFood = 8)
public class Wolf extends Predator {

    public Wolf(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
