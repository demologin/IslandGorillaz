package com.javarush.island.khmelov.entity.organizm.animals.herbivores;

import com.javarush.island.khmelov.api.annotation.TypeData;
import com.javarush.island.khmelov.entity.organizm.Limit;

@TypeData(name = "Буйвол", icon = "\uD83D\uDC03", maxWeight = 700, maxCountInCell = 10,
        flockSize = 2, maxSpeed = 3, maxFood = 100)
public class Buffalo extends Herbivore {
    public Buffalo(String name, String icon, Limit limit) {
        super(name, icon, limit);
    }

}
