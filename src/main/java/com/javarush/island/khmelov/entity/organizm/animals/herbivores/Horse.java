package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Лошадка", icon = "\uD83D\uDC0E", maxWeight = 400, maxCountInCell = 20,
        flockSize = 10, maxSpeed = 4, maxFood = 60)
public class Horse extends Herbivore {
    public Horse(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
