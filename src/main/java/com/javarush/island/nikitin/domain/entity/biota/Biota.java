package com.javarush.island.nikitin.domain.entity.biota;

import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Biota implements Cloneable {

    private static final AtomicLong idCounter = new AtomicLong();
    private static final double factorWeightLoss = 0.01d;
    @Setter
    @Getter
    private Props props;
    @Getter
    private PreferenceMap prefsMap;
    @Getter
    @Setter
    private Navigator navigator;

    @Setter
    @Getter
    private double weight;
    private int day;
    @Getter
    private long id = idCounter.getAndIncrement();

    public Biota(Props props) {
        this.props = props;
        weight = props.getMaxWeight();
    }

    public abstract boolean eat(Location habitat);

    public abstract void migrate(Location habitat);

    //todo this взят из копии коллекции локации, можно модифицировать основную коллекцию
    public final void survive(Location habitat, int generalDay) {
        if (this.day == generalDay) {
            /*boolean success = eat(habitat);
            if (success) {
                reproduce(habitat);
            } else {
                hunger(habitat);
            }

             */
            migrate(habitat);
            this.day++;
        }
    }

    private void hunger(Location habitat) {
        double finalWeight = weight - props.getMaxWeight() * factorWeightLoss;
        if (finalWeight > 0) {
            setWeight(finalWeight);
        } else {
            habitat.removeUnitLocation(this);
        }
    }


    //Todo принимает локацию где находится this, пробует добавить слона в популяцию, если не удачно то клон уничтожается
    // метод протестирован и работает
    public void reproduce(Location habitat) {
        if (habitat.checkPartner(sayMyNameCommunity())) {
            int splitBiota = 2;
            weight /= splitBiota;
            Biota clone = clone();
            boolean success = habitat.addUnitLocation(clone);
            if (!success) {
                clone = null;
            }
        }
    }

    public void die(Location habitat) {
        habitat.removeUnitLocation(this);
    }

    public final String sayMyNameCommunity() {
        return getClass().getSimpleName();
    }


    @Override
    public Biota clone() {
        try {
            Biota newBiota = (Biota) super.clone();
            newBiota.props = this.props.clone();
            newBiota.id = idCounter.getAndIncrement();
            return newBiota;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
