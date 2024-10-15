package com.javarush.island.levchuk.entities;

import com.javarush.island.levchuk.constants.Constants;
import com.javarush.island.levchuk.liveActions.Reproductive;
import com.javarush.island.levchuk.map.Cell;

import com.javarush.island.levchuk.utils.EntityFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity implements Reproductive {
    private String name;
    private String icon;
    private int amountMax;


    @Override
    public < T extends Entity> T reproduce(Cell cell) {
        T childEntity = (T) EntityFactory.getEntity(this.getClass());
        return childEntity;
    }
}
