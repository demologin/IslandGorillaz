package com.javarush.island.nikitin.domain.entity.biota.animals.herbivores;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;

@GameUnit
@InjectLimitData(maxWeight = 0.01d, maxCountUnit = 1000)
public class Caterpillar extends Herbivorous {
    public Caterpillar(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }
}