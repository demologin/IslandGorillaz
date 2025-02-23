package com.javarush.island.ivanov.entity.organism;

import com.javarush.island.ivanov.api.entity.Eating;
import com.javarush.island.ivanov.api.entity.Movable;
import com.javarush.island.ivanov.constants.Constants;
import com.javarush.island.ivanov.entity.map.Cell;
import com.javarush.island.ivanov.utils.OrganismsFactory;
import com.javarush.island.ivanov.utils.Randomizer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Getter
@Setter
public abstract class Animal extends Organism implements Eating, Movable {
    private double weight;
    private double weightDefault;
    private double weightSaturation;
    private int speedMax;
    private Map<String, Integer> likelyFood;


    @Override
    public void move(Cell cell) {
        cell.getNeighbors().stream().filter(n -> n.checkFreeSpace(this)).findFirst().ifPresent(n -> {
            n.addEntity(this);
            cell.removeEntity(this);
        });
    }

    @Override
    public void eat(Cell cell) {
        List<String> potentialFoodTypes = getPotentialFood();
        if (!potentialFoodTypes.isEmpty()) {
            String foodType = potentialFoodTypes.get(Randomizer.getRandomInt(potentialFoodTypes.size()));
            int chanceToEat = likelyFood.get(foodType);
            if (chanceToEat >= Randomizer.getRandomInt(Constants.MAX_CHANCE_TO_EAT)) {
                tryToEat(foodType, cell);
            }

            weight -= weightSaturation;
            if (minimumPossibleWeightThresholdHasBeenExceed()) {
                cell.removeEntity(this);
            }
        }
    }

    private List<String> getPotentialFood() {
        return likelyFood.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .filter(entityName -> OrganismsFactory.getOrganismClass(entityName) != null)
                .collect(Collectors.toList());
    }

    private boolean minimumPossibleWeightThresholdHasBeenExceed() {
        return weight / ((Animal) OrganismsFactory.getOrganism(getClass())).getWeightDefault() < Constants.MIN_PERCENT_WEIGHT_TO_DIE;
    }

    private void tryToEat(String selectedFood, Cell cell) {
        cell.getResidents().computeIfPresent(OrganismsFactory.getOrganismClass(selectedFood), (food, entities) -> {
            if (!entities.isEmpty()) {
                Organism entity = entities.getFirst();
                double saturation = (entity instanceof Animal ? ((Animal) entity).getWeight() : weightSaturation);
                weight += saturation;
                entities.removeFirst();
            }
            return entities;
        });
    }


}
