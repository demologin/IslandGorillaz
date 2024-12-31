package com.javarush.island.nikitin.domain.entity.biota.animals.predators;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

@GameUnit
@InjectLimitData(maxWeight = 15d, maxSpeed = 1, maxFoodFeed = 3d, maxCountUnit = 30)
public class Boa extends Predator {
    public Boa(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }
}
