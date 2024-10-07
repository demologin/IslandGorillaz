package com.javarush.island.khmelov.entity.organizm.animals.predators;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Орел", icon = "\uD83E\uDD85", maxWeight = 6, maxCountInCell = 20,
        flockSize = 4, maxSpeed = 3, maxFood = 1)
public class Eagle extends Predator {

    public Eagle(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
