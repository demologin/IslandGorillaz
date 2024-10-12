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
    public void eat(Cell cell) {
        List<String> foodTypes = likelyFood
                .entrySet().stream()
                .filter(m -> m.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (!foodTypes.isEmpty()) {
            String food = foodTypes.get(Randomizer.getRandomInt(foodTypes.size()));
            Integer chanceToEat = likelyFood.get(food);
            if (chanceToEat >= Randomizer.getRandomInt(Constants.MAX_CHANCE_TO_EAT)) {
                Class<? extends Entity> targetFood = EntityFactory.getEntityClass(food);
                List<Entity> entityList = cell.getResidents().get(targetFood);
                if (entityList != null && !entityList.isEmpty()) {
                    double saturation = 0;
                    if (entityList.get(0) instanceof Animal) {
                        saturation = ((Animal) entityList.get(0)).getWeightDefault();
                    } else {
                        saturation = weightSaturation;
                    }
                    weight = weight + saturation;
                    cell.removeEntity(entityList.get(0));
                }
            }
        }
        weight = weight - weightSaturation;
        if (weight / ((Animal) EntityFactory.getEntity(this.getClass())).getWeightDefault() < Constants.MIN_PERCENT_WEIGHT_TO_DIE) {
            cell.removeEntity(this);
        }
    }

    @Override
    public void move(Cell cell) {
        Cell targetCell = null;
        for (int i = 0; i < this.getSpeedMax(); i++) {
            Cell temp = cell.getNeighbors().stream().filter(n -> n != null && n.checkFreeSpace(this)).findFirst().get();
            if (temp == null) {
                break;
            }
            targetCell = temp;
        }
        if (targetCell != null) {
            targetCell.addEntity(this);
            cell.removeEntity(this);
        }
    }
}
