package com.javarush.island.nikitin.domain.entity.biota.animals;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.map.navigation.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Biota {

    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);

    public Animal(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }

    @Override
    public boolean eat(Biota prey, Location habitat) {
        var preyProperty = prey.getProperty();
        var thisProperty = getProperty();
        if (!prey.getIsAlive().get()) {
            throw new RuntimeException("eat not life " + this + " prey " + prey);
        }

        double preyWeight = preyProperty.getWeight();
        double thisWeight = thisProperty.getWeight();

        double maxWeight = this.getLimitData().maxWeight();
        double sizePiece = maxWeight - thisWeight;
        if (sizePiece == 0) {

            //prey.getLockerBiota().unlock();
            throw new RuntimeException("sizePiece == 0");
            //return false;
        }
        if (preyWeight >= sizePiece) {
            thisWeight += sizePiece;
            preyWeight -= sizePiece;
            thisProperty.setWeight(thisWeight);
            if (prey.isCriticalWeight(preyWeight)) {
                preyProperty.setWeight(preyWeight);
                prey.death(habitat);
                LOGGER.debug("\t\t\t4 IAM {}, - iam poel and prey isCriticalWeight dead - {} ", this, prey);

            } else {
                preyProperty.setWeight(preyWeight);
                LOGGER.debug("\t\t\t4 IAM {}, - iam poel and prey is live - {} ", this, prey);
            }
        } else {
            thisWeight += preyWeight;
            preyWeight = 0;
            thisProperty.setWeight(thisWeight);
            preyProperty.setWeight(preyWeight);
            prey.death(habitat);
            LOGGER.debug("\t\t\t4 IAM {}, - iam poel and prey is dead - {} ", this, prey);
        }
        //prey.getLockerBiota().unlock();

        return true;
    }

    //todo метод для безопасного удаления из коллекции

    @Override
    public Optional<Biota> findPrey(Location habitat, PreferenceMenu preferenceMenu) {
        Optional<Map.Entry<String, Integer>> anyItemMenu = preferenceMenu.getAnyItemMenu();

        return anyItemMenu.flatMap(stringIntegerEntry -> {
            String foodName = stringIntegerEntry.getKey();
            Integer choice = stringIntegerEntry.getValue();
            if (isChoiceSuccess(choice) && habitat.isPresentPopulation(foodName)) {

                Optional<Biota> assessPrey = habitat.getRandomBiotaByNameCommunity(foodName);
                LOGGER.debug("\t2 IAM {}, - my findPrey {} ", this, assessPrey);
                return assessPrey;
            } else {
                LOGGER.debug("\t2 IAM {}, - my findPrey NULLIBLE ", this);
            }
            return Optional.empty();

        });
    }

    @Override
    public void migrate(Location habitat) {
        LOGGER.debug("\t\t\t\t5 migrate I am: {} my current location: {}",
                this,
                habitat.localId);

        int countStep = makeCountStepRandom();
        if (countStep != 0) {
            Direction direction = Direction.getRandomDirection();
            Location newLocation = getNavigator().findNewLocation(direction, habitat, countStep);
            boolean result = newLocation.addUnitLocation(this);
            if (result) {
                LOGGER.debug("\t\t\tI am: {} my new location: {}",
                        this,
                        newLocation.localId);
                habitat.removeUnitLocation(this);
            }
        }
    }

    private boolean isChoiceSuccess(int choice) {
        int pctBound = 100;
        return choice > ThreadLocalRandom.current().nextInt(pctBound);
    }

    private int makeCountStepRandom() {
        int bound = 1;
        int maxCountStepBound = this.getLimitData().maxSpeed() + bound;
        return ThreadLocalRandom.current().nextInt(maxCountStepBound);
    }
}
