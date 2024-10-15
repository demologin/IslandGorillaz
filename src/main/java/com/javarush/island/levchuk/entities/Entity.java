package com.javarush.island.levchuk.entities;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.liveActions.Reproductive;
import com.javarush.island.levchuk.map.Cell;

import com.javarush.island.levchuk.utils.EntityFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@Setter
public class Entity implements Reproductive {
    private String name;
    private String icon;
    private int amountMax;
    private boolean isReproduced;

    @Override
    public < T extends Entity> T reproduce(Cell cell) {
        if (!isReproduced){
            List< Entity > entities = cell.getResidents().get(getClass());
            if (entities.size() < amountMax){
                Optional<Entity> nextParent = entities.stream().filter(Predicate.not(Entity::isReproduced)).findFirst();
                if (nextParent.isPresent()){
                    T newEntity = (T) EntityFactory.getEntity(this.getClass());
                    nextParent.get().setReproduced(true);
                    newEntity.setReproduced(true);
                    return newEntity;
                }
            }
        }
        return null;
    }
}
