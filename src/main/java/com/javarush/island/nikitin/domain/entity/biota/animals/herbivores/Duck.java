package com.javarush.island.nikitin.domain.entity.biota.animals.herbivores;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

@GameUnit
@InjectLimitData(maxWeight = 1d, maxSpeed = 4, maxFoodFeed = 0.15d, maxCountUnit = 200)
public class Duck extends Herbivorous {
    public Duck(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }
}