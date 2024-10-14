package com.javarush.island.gerasimov.entity.map;
import com.javarush.island.gerasimov.entity.creatures.Organism;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Cell {

    private final Lock lock = new ReentrantLock();
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;
    private CopyOnWriteArrayList<Organism> organisms;
    private int row;
    private int col;

    public Cell() {
        id = COUNTER.getAndIncrement();
    }

    @Override
    public String toString() {
        return "Ячейка" + id  +
                ", множество=" + organisms +
                '}';
    }
}
