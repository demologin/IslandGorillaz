package com.javarush.island.myasnikov.entity.organism;

import com.javarush.island.myasnikov.map.Cell;
import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Organism {

    @Setter
    private Cell currentCell;

    @Getter
    private OrganismTypes type;

    @Getter
    private String icon;

    @Getter
    @Setter
    private boolean isAlive = true;

    private double weight;

    @Getter
    private final Limit limit;

    public Organism(OrganismTypes type, String icon, double weight, Limit limit) {
        this.type = type;
        this.icon = icon;
        this.weight = weight;
        this.limit = limit;
    }

    public void addWeight(double weight) {
        this.weight += weight;
        double maxWeight = this.getLimit().getMaxWeight();
        if (this.weight > maxWeight) {
            this.weight = maxWeight;
        }
    }

    public void reduceWeight(double weight) {
        this.weight -= weight;
    }

    public int getEatChance (Organism organism, int[][] eatingChanceTable) {
        int eatingOrganism = this.getType().ordinal();
        int eatenOrganism = organism.getType().ordinal();
        return eatingChanceTable[eatingOrganism][eatenOrganism];
    }

    public void move() {
    }

    public void eat() {
    }

    public void reproduce() {
    }

    public void action() {

    }
}
