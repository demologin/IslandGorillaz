package com.javarush.island.stepanov.entity.map;

import com.javarush.island.stepanov.entity.oganism.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private final List<Cell> nextCell = new ArrayList<>();
    @Getter
    private final Lock lock = new ReentrantLock(true);
    @Getter
    @Setter
    private final HashMap<String,List<Organism>> residentMap = new HashMap();


}
