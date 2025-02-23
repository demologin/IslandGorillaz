package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.OrganismLimitData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@OrganismLimitData(name = "Кролик", icon = "\uD83D\uDC07", maxWeight = 2, maxCountInCell = 150,
        flockSize = 50, maxSpeed = 2, maxFood = 0.45)
public class Rabbit extends Herbivore {
    public Rabbit(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
