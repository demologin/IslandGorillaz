package com.javarush.island.gerasimov.intefaces;

import com.javarush.island.gerasimov.entity.map.Cell;
import com.javarush.island.gerasimov.entity.map.GameMap;

public interface MoveAble {

    boolean move(Cell cell);

}
