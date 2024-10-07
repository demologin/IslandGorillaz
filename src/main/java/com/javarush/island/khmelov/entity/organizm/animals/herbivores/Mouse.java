package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Мышка", icon = "\uD83D\uDC01", maxWeight = 0.05, maxCountInCell = 500,
        flockSize = 50, maxSpeed = 1, maxFood = 0.01)
public class Mouse extends Herbivore {
    public Mouse(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
