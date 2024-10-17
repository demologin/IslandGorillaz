package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Утка", icon = "\uD83E\uDD86", maxWeight = 1, maxCountInCell = 200,
        flockSize = 50, maxSpeed = 4, maxFood = 0.15)
public class Duck extends Herbivore {
    public Duck(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
