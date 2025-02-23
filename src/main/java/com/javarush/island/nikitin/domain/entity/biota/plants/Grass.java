package com.javarush.island.nikitin.domain.entity.biota.plants;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import com.javarush.island.nikitin.domain.constants.LogMessagesDmn;
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
        Optional<Biota> assessPrey = Optional.empty();
        LOGGER.debug(LogMessagesDmn.FIND_PREY_MY_PREY_CANDIDATE, this, assessPrey);
        return assessPrey;
    }

    @Override
    public boolean eat(Biota prey, Location habitat) {
        LOGGER.debug(LogMessagesDmn.EAT_START, this, prey);
        double maxWeight = getLimitData().maxWeight();
        updateWeight(maxWeight);
        return true;
    }

    @Override
    public void reproduce(Location habitat, boolean successfulLunch) {
        Biota clone = clone();
        clone.currentDayIsFinish();
        if (habitat.addUnitLocation(clone)) {
            LOGGER.debug(LogMessagesDmn.REPRODUCE_ASSESS, clone, habitat);
        }
    }

    @Override
    public void migrate(Location habitat) {
    }
}
