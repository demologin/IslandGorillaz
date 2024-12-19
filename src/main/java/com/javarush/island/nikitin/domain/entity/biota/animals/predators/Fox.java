package com.javarush.island.nikitin.domain.entity.biota.animals.predators;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.entity.biota.Props;

@GameUnit
@InjectProps(name = "fox", icon = "\ud83d\udc3b", maxWeight = 8d, maxSpeed = 2, maxFoodFeed = 2d, maxCountUnit = 30)
public class Fox extends Predator {
    public Fox(Props props) {
        super(props);
    }
}
