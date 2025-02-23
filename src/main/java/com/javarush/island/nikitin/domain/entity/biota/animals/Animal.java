package com.javarush.island.nikitin.domain.entity.biota.animals;

import com.javarush.island.nikitin.domain.constants.LogMessagesDmn;
import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.navigation.Direction;
import com.javarush.island.nikitin.domain.util.Biotas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

public abstract class Animal extends Biota {

    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);

    public Animal(LimitData limitData, Property property, PreferenceMenu preferenceMenu) {
        super(limitData, property, preferenceMenu);
    }

    @Override
    public Optional<Biota> findPrey(Location habitat, PreferenceMenu preferenceMenu) {
        Optional<Map.Entry<String, Integer>> anyItemMenu = preferenceMenu.getAnyItemMenu();

        return anyItemMenu.flatMap(stringIntegerEntry -> {
            String foodName = stringIntegerEntry.getKey();
            Integer choice = stringIntegerEntry.getValue();
            if (Biotas.isChoiceSuccess(choice) && habitat.isPresentPopulation(foodName)) {

                Optional<Biota> assessPrey = habitat.getRandomBiotaByNameCommunity(foodName);
                LOGGER.debug(LogMessagesDmn.FIND_PREY_MY_PREY_CANDIDATE, this, assessPrey);
                return assessPrey;
            }
            return Optional.empty();

        });
    }

    @Override
    public boolean eat(Biota prey, Location habitat) {
        LOGGER.debug(LogMessagesDmn.EAT_START, this, prey);
        Biotas.checkLifeStatus(prey);
        if (Biotas.isAtMaxWeight(this)) {
            return false;
        }
        double sizePiece = Biotas.computeSizeOfPiece(this);
        double futureWeightPrey = Biotas.fetchWeight(prey) - sizePiece;
        if (Biotas.isCriticalWeight(prey, futureWeightPrey)) {
            sizePiece = Biotas.fetchWeight(prey);
            prey.death(habitat);
        }
        consumePreyPiece(prey, sizePiece);
        LOGGER.debug(LogMessagesDmn.EAT_COMPLETE, this, prey);
        return true;
    }

    @Override
    public void migrate(Location habitat) {
        LOGGER.debug(LogMessagesDmn.MIGRATE_MY_CURRENT_LOCATION, this, habitat);
        int countStep = Biotas.makeCountStepRandom(this);
        if (countStep != 0) {
            Direction direction = Direction.getRandomDirection();
            Location newLocation = getNavigator().findNewLocation(direction, habitat, countStep);
            if (newLocation != habitat) {
                boolean result = newLocation.addUnitLocation(this);
                if (result) {
                    LOGGER.debug(LogMessagesDmn.MIGRATE_MY_NEW_LOCATION, this, newLocation);
                    habitat.removeUnitLocation(this);
                }
            }
        }
    }

    private void consumePreyPiece(Biota prey, double sizePiece) {
        double preyWeight = Biotas.fetchWeight(prey);
        double thisWeight = Biotas.fetchWeight(this);
        preyWeight -= sizePiece;
        thisWeight += sizePiece;
        prey.updateWeight(preyWeight);
        updateWeight(thisWeight);
    }
}
