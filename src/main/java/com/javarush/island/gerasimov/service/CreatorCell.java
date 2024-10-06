package com.javarush.island.gerasimov.service;
import com.javarush.island.gerasimov.entity.map.Cell;
import java.util.ArrayList;
import java.util.List;

public class CreatorCell {

    private final List<Cell> cells = new ArrayList<>();

    public void initialisation() {
       for (int i = 0; i < 4; i++) {
           Cell cell = new Cell();
           cells.add(cell);
       }
    }

    public List<Cell> getCells() {
        return cells;
    }
}
