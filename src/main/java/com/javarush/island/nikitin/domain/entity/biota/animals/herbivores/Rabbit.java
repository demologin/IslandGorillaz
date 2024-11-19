package com.javarush.island.nikitin.domain.entity.biota.animals.herbivores;

import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.entity.biota.Props;

@GameUnit
@InjectProps(name = "rabbit", icon = "\uD83D\uDC07", maxWeight = 2d, maxSpeed = 2, maxFoodFeed = 0.45d, maxCountUnit = 150)
public class Rabbit extends Herbivorous {
    public Rabbit(Props props) {
        super(props);
    }


}
