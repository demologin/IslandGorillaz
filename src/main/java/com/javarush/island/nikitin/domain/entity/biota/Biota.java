package com.javarush.island.nikitin.domain.entity.biota;

import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public abstract class Biota implements Cloneable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Biota.class);

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private static final double PCT_WEIGHT_LOSS = 0.01d;
    private static final double PCT_WEIGHT_FOR_SURVIVAL = 0.05d;
    private static final double PCT_FOR_REPRODUCE = 0.1d;
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

    //todo this взят из копии коллекции локации, можно модифицировать основную коллекцию
    public final void survive(Location habitat, int calendarDay) {
        //System.out.println(calendarDay + " dddddddddddddddddddd " + this.getCurrentDay());
        if (!isAlive.get()) {
            throw new RuntimeException("isAlive: " + isAlive + " " + getId() + " day " + currentDay + " " + calendarDay + " " + habitat.localId + this);
        }
        if (this.currentDay == calendarDay) {
            loggOnlyThis("1 Start");

            dailyEnergyExpenditure(habitat);
            if (isAlive.get()) {
                Optional<Biota> preyWrapper = findPrey(habitat, getPreferenceMenu());
                preyWrapper.ifPresent(prey -> loggThisAndPrey("\t\t3 Before eat ", prey, habitat));

                boolean successfulLunch = preyWrapper.map(biota -> this.eat(biota, habitat)).orElse(false);

                preyWrapper.ifPresent(prey -> loggThisAndPrey("\t\t\t\t5 After eat ", prey, habitat));

                reproduce(habitat, successfulLunch);
                migrate(habitat); //работает
                currentDayIsFinish();
            }
        }
    }

    public abstract Optional<Biota> findPrey(Location habitat, PreferenceMenu preferenceMenu);

    public abstract boolean eat(Biota prey, Location habitat);

    public abstract void migrate(Location habitat);

    public void reproduce(Location habitat, boolean successfulLunch) {

        if (successfulLunch &&
                habitat.checkPartner(sayMyNameCommunity()) &&
                successfulReproduce()) {
            int splitBiota = 2;
            double futureWeight = property.getWeight() / splitBiota;
            if (isCriticalWeight(futureWeight)) {
                return;
            }
            property.setWeight(futureWeight);
            Biota clone = clone();
            clone.currentDayIsFinish();
            if (habitat.addUnitLocation(clone)) {
                loggThisAndPrey("reproduce Assess,", clone, habitat);
            } else {
                loggThisAndPrey("reproduce Fail,", clone, habitat);
            }

        }
    }

    public void dailyEnergyExpenditure(Location habitat) {
        double updatedWeight = calculateUpdatedWeight();
        if (isCriticalWeight(updatedWeight)) {
            death(habitat);
        } else {
            property.setWeight(updatedWeight);
        }
    }

    public final void death(Location habitat) {
        loggOnlyThis("Death ");
        if (habitat.removeUnitLocation(this)) {
            isAlive.set(false);
        }
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

    private boolean successfulReproduce() {
        return ThreadLocalRandom.current().nextDouble() < PCT_FOR_REPRODUCE;
    }

    private double calculateUpdatedWeight() {
        double dailyEnergyExpenditure = limitData.maxWeight() * PCT_WEIGHT_LOSS;
        return property.getWeight() - dailyEnergyExpenditure;
    }

    public boolean isCriticalWeight(double targetWeight) {
        double minWeightForSurvival = limitData.maxWeight() * PCT_WEIGHT_FOR_SURVIVAL;
        return targetWeight < minWeightForSurvival;
    }

    public final String sayMyNameCommunity() {
        return getClass().getSimpleName();
    }

    public final void currentDayIsFinish() {
        this.currentDay++;
    }

    @Override
    public String toString() {
        return "Biota { " +
                "name=" + sayMyNameCommunity() +
                ", id=" + id +
                ", isAlive=" + isAlive.get() +
                ", currentDay=" + currentDay +
                ", weight=" + property.getWeight() +
                '}';
    }

    private void loggOnlyThis(String message) {
        LOGGER.debug("{} I am: {}",
                message, this);
    }

    private void loggThisAndPrey(String message, Biota prey, Location habitat) {
        LOGGER.debug("{} I am: {}, id: {}, w: {} isAlive: {} Location: {} - My prey: {}, id: {}, w: {} isAlive: {} ",
                message,
                sayMyNameCommunity(),
                id,
                property.getWeight(),
                isAlive.get(),
                habitat.localId,
                prey.sayMyNameCommunity(),
                prey.getId(),
                prey.property.getWeight(),
                prey.isAlive.get());
    }
}
