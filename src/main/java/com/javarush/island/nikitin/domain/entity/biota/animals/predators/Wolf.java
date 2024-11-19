package com.javarush.island.nikitin.domain.entity.biota.animals.predators;

import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.entity.biota.Props;

@GameUnit
@InjectProps(name = "wolf", icon = "\uD83D\uDC3A", maxWeight = 50d, maxSpeed = 3, maxFoodFeed = 8d, maxCountUnit = 30)
public class Wolf extends Predator {
    public Wolf(Props props) {
        super(props);
    }


}
