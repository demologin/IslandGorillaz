package com.javarush.island.nikitin.domain.entity.biota.animals.herbivores;

import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.entity.biota.Props;

@GameUnit
@InjectProps(name = "mouse", icon = "\ud83d\udc01", maxWeight = 0.05d, maxSpeed = 1, maxFoodFeed = 0.01d, maxCountUnit = 500)
public class Mouse extends Herbivorous{
    public Mouse(Props props) {
        super(props);
    }

}
