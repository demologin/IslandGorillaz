package com.javarush.island.nikitin.domain.entity.biota;

import com.javarush.island.nikitin.domain.constants.LogMessagesDmn;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.navigation.Navigator;
import com.javarush.island.nikitin.domain.util.Biotas;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public abstract class Biota implements Cloneable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Biota.class);
    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private AtomicBoolean isAlive = new AtomicBoolean(true);
    @EqualsAndHashCode.Include
    private long id = ID_COUNTER.getAndIncrement();
    @Setter
    private int currentDay;
    @Setter
    private Navigator navigator;
    private Property property;
    private final LimitData limitData;
    private final PreferenceMenu preferenceMenu;

    public Biota(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        this.limitData = limitData;
        this.property = property;
        this.preferenceMenu = preferenceMenu;
    }

    public final void survive(Location habitat, int calendarDay) {
        Biotas.checkLifeStatus(this);
        if (this.currentDay == calendarDay) {
            LOGGER.debug(LogMessagesDmn.SURVIVE_START, this, habitat);

            Optional<Biota> preyCandidate = findPrey(habitat, preferenceMenu);
            boolean successfulLunch = preyCandidate
                    .map(prey -> eat(prey, habitat))
                    .orElse(false);
            reproduce(habitat, successfulLunch);
            migrate(habitat);
            dailyEnergyExpenditure(habitat);
            currentDayIsFinish();

            LOGGER.debug(LogMessagesDmn.SURVIVE_FINISH, this);
        } else {
            LOGGER.debug(LogMessagesDmn.PASS_IAM_CALENDAR_DAY, this, calendarDay);
        }
    }

    public abstract Optional<Biota> findPrey(Location habitat, PreferenceMenu preferenceMenu);

    public abstract boolean eat(Biota prey, Location habitat);

    public abstract void migrate(Location habitat);

    public void reproduce(Location habitat, boolean successfulLunch) {
        boolean hasPartner = hasPartner(habitat);
        boolean successRollReproduce = Biotas.isSuccessRollReproduce(this);
        if (successfulLunch && hasPartner && successRollReproduce) {
            int splitBiota = 2;
            double currentWeight = Biotas.fetchWeight(this);
            double futureWeight = currentWeight / splitBiota;
            boolean isCriticalWeight = Biotas.isCriticalWeight(this, futureWeight);
            if (isCriticalWeight) {
                return;
            }
            updateWeight(futureWeight);
            Biota clone = clone();
            clone.currentDayIsFinish();
            if (habitat.addUnitLocation(clone)) {
                LOGGER.debug(LogMessagesDmn.REPRODUCE_ASSESS, clone, habitat);
            }
        }
    }

    public final void dailyEnergyExpenditure(Location habitat) {
        double weightAfterDailyLoss = Biotas.calculateWeightAfterDailyLoss(this);
        if (Biotas.isCriticalWeight(this, weightAfterDailyLoss)) {
            death(habitat);
        } else {
            updateWeight(weightAfterDailyLoss);
        }
    }

    public final void death(Location habitat) {
        LOGGER.debug(LogMessagesDmn.DEATH, this);
        if (habitat.buryInGrave(this)) {
            isAlive.set(false);
        }
    }

    public final void currentDayIsFinish() {
        this.currentDay++;
    }

    public final void updateWeight(double weight) {
        property.setWeight(weight);
    }

    @Override
    public Biota clone() {
        try {
            Biota newBiota = (Biota) super.clone();
            newBiota.property = this.property.clone();
            newBiota.id = ID_COUNTER.getAndIncrement();
            newBiota.isAlive = new AtomicBoolean(true);
            return newBiota;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasPartner(Location habitat) {
        int minPartner = 1;
        String nameCommunity = Biotas.sayMyNameCommunity(this);
        return habitat.checkPartner(nameCommunity, minPartner);
    }

    @Override
    public String toString() {
        return "Biota { " +
                "name=" + Biotas.sayMyNameCommunity(this) +
                ", id=" + id +
                ", isAlive=" + isAlive.get() +
                ", currentDay=" + currentDay +
                ", weight=" + Biotas.fetchWeight(this) +
                '}';
    }
}
