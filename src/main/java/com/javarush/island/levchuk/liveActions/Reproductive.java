package com.javarush.island.levchuk.liveActions;

import com.javarush.island.levchuk.entities.Entity;
import com.javarush.island.levchuk.map.Cell;

public interface Reproductive {
     <T extends Entity> T  reproduce(Cell cell);
}