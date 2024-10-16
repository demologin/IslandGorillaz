package com.javarush.island.levchuk.entities;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.liveActions.Eating;
import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;
import com.javarush.island.levchuk.utils.EntityFactory;
import com.javarush.island.levchuk.utils.Randomizer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Animal extends Entity implements Eating, Movable {
    private double weight;
    private double weightDefault;
    private double weightSaturation;
    private int speedMax;
    private Map<String, Integer> likelyFood;


    @Override
    public void move(Cell cell) {
/*        Cell nextCell;
        for (int i = 0; i < this.getSpeedMax(); i++) {
             nextCell = cell.getNeighbors().stream().filter(n ->  n.checkFreeSpace(this)).findFirst().get();
            if (nextCell!=cell && cell!=null) {
                nextCell.addEntity(this);
                cell.removeEntity(this);

            }
        }*/
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
                .filter(entityName -> EntityFactory.getEntityClass(entityName) != null)
                .collect(Collectors.toList());
    }

    private boolean minimumPossibleWeightThresholdHasBeenExceed() {
        return weight / ((Animal) EntityFactory.getEntity(getClass())).getWeightDefault() < Constants.MIN_PERCENT_WEIGHT_TO_DIE;
    }

    private void tryToEat(String selectedFood, Cell cell) {
        cell.getResidents().computeIfPresent(EntityFactory.getEntityClass(selectedFood), (food, entities) -> {
            if (!entities.isEmpty()) {
                Entity entity = entities.get(0);
                double saturation = (entity instanceof Animal ? ((Animal) entity).getWeight() : weightSaturation);
                weight += saturation;
                entities.remove(0);
            }
            return entities;
        });
    }
}
