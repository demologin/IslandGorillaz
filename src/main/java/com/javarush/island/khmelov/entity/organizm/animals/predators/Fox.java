package com.javarush.island.khmelov.entity.organizm.animals.predators;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Лиса", icon = "\uD83E\uDD8A", maxWeight = 8, maxCountInCell = 30,
        flockSize = 5, maxSpeed = 2, maxFood = 2)
public class Fox extends Predator {

    public Fox(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
