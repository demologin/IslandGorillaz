package com.javarush.island.nikitin.domain.entity.biota.animals;

import com.javarush.island.nikitin.domain.entity.biota.Biota;
import com.javarush.island.nikitin.domain.entity.biota.Props;
import com.javarush.island.nikitin.domain.entity.map.Location;
import com.javarush.island.nikitin.domain.entity.map.navigation.Direction;
import com.javarush.island.nikitin.domain.entity.map.navigation.MoveStrategy;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Biota {
    public Animal(Props props) {
        super(props);
    }

    @Override
    public boolean eat(Location habitat) {

        Optional<Biota> target = findPrey(habitat, "");
        if (target.isEmpty()) {
            return false;
        }
        Biota targetUnit = target.get();

        double targetUnitWeight = targetUnit.getWeight();
        double currentUnitWeight = this.getWeight();

        double minUnitWeight = 0.05d;
        double maxWeight = this.getProps().getMaxWeight();

        double sizePiece = maxWeight - currentUnitWeight;

        if (targetUnitWeight >= sizePiece) {
            currentUnitWeight += sizePiece;
            targetUnitWeight -= sizePiece;

            setWeight(currentUnitWeight);
            targetUnit.setWeight(targetUnitWeight);
        } else {
            currentUnitWeight += targetUnitWeight;
            setWeight(currentUnitWeight);

            targetUnit.die(habitat);
        }
        if ((maxWeight * minUnitWeight) > targetUnitWeight) {
            this.die(habitat);
        }

        return true;
    }
    //todo метод для безопасного удаления из коллекции


    private Optional<Biota> findPrey(Location habitat, String listFavoriteFood) {

        if (sayMyNameCommunity().equals("Mouse")) {
            return habitat.getPopulationByName("Grass").stream().findFirst();
        }
        if (sayMyNameCommunity().equals("Wolf")) {
            return habitat.getPopulationByName("Rabbit").stream().findFirst();
        }
        if (sayMyNameCommunity().equals("Rabbit")) {
            return habitat.getPopulationByName("Grass").stream().findFirst();
        }
        return Optional.empty();
    }

    @Override
    public void migrate(Location habitat) {
        int maxStepBound = this.getProps().getMaxSpeed() + 1;
        int stepRandom = ThreadLocalRandom.current().nextInt(maxStepBound);

        Direction randomDirection = Direction.getRandomDirection();
        Location newLocation = getNavigator().execute(randomDirection, habitat, stepRandom);
        if (!(newLocation == habitat)) {
            boolean result = newLocation.addUnitLocation(this);
            if (result) {
                habitat.removeUnitLocation(this);
            }
        }
    }
}
