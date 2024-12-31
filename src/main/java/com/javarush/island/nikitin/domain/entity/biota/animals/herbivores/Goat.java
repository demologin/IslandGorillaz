package com.javarush.island.nikitin.domain.entity.biota.animals.herbivores;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

@GameUnit
@InjectLimitData(maxWeight = 60d, maxSpeed = 3, maxFoodFeed = 10d, maxCountUnit = 140)
public class Goat extends Herbivorous {
    public Goat(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }
}
