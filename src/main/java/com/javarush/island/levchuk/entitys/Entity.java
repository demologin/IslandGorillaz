package com.javarush.island.levchuk.entitys;

import com.javarush.island.levchuk.liveActions.Movable;
import com.javarush.island.levchuk.map.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Entity  {
    private String name;
    private String icon;
    private int amountMax;
    private boolean isReproduced = false;


}
