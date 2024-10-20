package com.javarush.island.gerasimov.entity.map;

import com.javarush.island.gerasimov.entity.creatures.Organism;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@ToString
public class Cell {

    private int id;
    private CopyOnWriteArrayList<Organism> organisms;
    private int row;
    private int col;
}
