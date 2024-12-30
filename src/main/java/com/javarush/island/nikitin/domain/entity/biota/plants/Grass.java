package com.javarush.island.nikitin.domain.entity.biota.plants;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;
import com.javarush.island.nikitin.domain.entity.map.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@GameUnit
@InjectLimitData(maxWeight = 1d, maxCountUnit = 200)
public class Grass extends Biota {

    private static final Logger LOGGER = LoggerFactory.getLogger(Grass.class);

    public Grass(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }

    @Override
    public Optional<Biota> findPrey(Location habitat, PreferenceMenu preferenceMenu) {

        Optional<Biota> empty = Optional.empty();
        LOGGER.debug("\t2 IAM {}, - my findPrey {} ", this, empty);
        return empty;
    }

    @Override
    public boolean eat(Biota prey, Location habitat) {
        LOGGER.debug("{}", this);
        return true;
    }

    @Override
    public void reproduce(Location habitat, boolean successfulLunch) {
       // System.out.println(" reproduce " + this);
        Biota clone = clone();
        clone.currentDayIsFinish();
        habitat.addUnitLocation(clone);
        if (successfulLunch) {
            reproduce(habitat, false);
        }

    }

    @Override
    public void migrate(Location habitat) {
    }

    @Override
    public void dailyEnergyExpenditure(Location habitat) {
        getProperty().setWeight(getLimitData().maxWeight());
    }
}
