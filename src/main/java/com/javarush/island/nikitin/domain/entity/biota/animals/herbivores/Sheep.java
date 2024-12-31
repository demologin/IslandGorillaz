package com.javarush.island.nikitin.domain.entity.biota.animals.herbivores;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

@GameUnit
@InjectLimitData(maxWeight = 70d, maxSpeed = 3, maxFoodFeed = 15d, maxCountUnit = 140)
public class Sheep extends Herbivorous {
    public Sheep(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }
}