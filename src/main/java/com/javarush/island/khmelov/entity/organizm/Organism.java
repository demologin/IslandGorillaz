package com.javarush.island.khmelov.entity.organizm;

import com.javarush.island.khmelov.api.entity.Reproducible;
import com.javarush.island.khmelov.entity.map.Cell;
import com.javarush.island.khmelov.util.Rnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode(of = "id")
public abstract class Organism implements Reproducible, Cloneable {

    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

    public Organism(String name, String icon, Limit limit) {
        this.name = name;
        this.icon = icon;
        this.limit = limit;
        weight = Rnd.random(limit.getMaxWeight() / 2, limit.getMaxWeight());
    }

    private long id = idCounter.incrementAndGet();
    private final String type = this
            .getClass()
            .getSimpleName();
    private final String name;
    private final String icon;

    private transient final String letter = type.substring(0, 1);
    @Setter
    private double weight;
    private final Limit limit;

    @Override
    public String toString() {
        return icon;
    }

    @Override
    protected Organism clone() throws CloneNotSupportedException {
        //visible in inherits (cast to Organism)
        Organism clone = (Organism) super.clone();
        clone.id = idCounter.incrementAndGet();
        clone.weight = Rnd.random(limit.getMaxWeight() / 2, limit.getMaxWeight());
        return clone;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Organism> T clone(T original) {
        //for clients (cast to original Type)
        try {
            return (T) original.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }


    protected boolean safeDie(Cell target) {
        //logic
        return false;
    }

    protected boolean safeChangeWeight(Cell currentCell, int percent) {
        //logic
        return false;
    }


    protected boolean safeMove(Cell source, Cell destination) {
        //logic
        return false;
    }

    protected boolean safeAddTo(Cell cell) {
        //logic
        return false;
    }

    protected boolean safePollFrom(Cell cell) {
        //logic
        return false;
    }

    protected boolean safeFindFood(Cell currentCell) {
        //logic
        return false;
    }

    private double getNeedFood() {
        return Math.min(
                getLimit().getMaxFood(),
                getLimit().getMaxWeight() - getWeight());
    }

}
