package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.OrganismLimitData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@OrganismLimitData(name = "Олень", icon = "\uD83E\uDD8C", maxWeight = 300, maxCountInCell = 20,
        flockSize = 5, maxSpeed = 4, maxFood = 60)
public class Deer extends Herbivore {
    public Deer(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
