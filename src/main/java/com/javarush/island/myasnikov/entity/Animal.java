package com.javarush.island.myasnikov.entity;


import com.javarush.island.myasnikov.api.CanEat;
import com.javarush.island.myasnikov.api.CanMove;
import com.javarush.island.myasnikov.api.CanReproduce;
import com.javarush.island.myasnikov.entity.organism.Organism;
import com.javarush.island.myasnikov.entity.organism.OrganismFactory;
import com.javarush.island.myasnikov.map.Cell;
import com.javarush.island.myasnikov.utility.Limit;
import com.javarush.island.myasnikov.utility.OrganismTypes;
import com.javarush.island.myasnikov.config.Params;


import static com.javarush.island.myasnikov.utility.RandomNumberGenerator.getRandomInt;

abstract public class Animal extends Organism implements CanMove, CanEat, CanReproduce {

    public Animal(OrganismTypes type, String icon, double weight, Limit limit) {
        super(type, icon, weight, limit);
    }

    public synchronized void eat() {
        if (this.isAlive() ) {
            Cell currentCell = this.getCurrentCell();
            for (Organism toBeEatenOrganism : currentCell.getCellOrganisms()) {
                if (!this.equals(toBeEatenOrganism) && getRandomInt(1, 100) <= this.getEatChance(toBeEatenOrganism, Params.getEatProbablyTable())) {
                    double weightEaten = toBeEatenOrganism.getWeight();
                    toBeEatenOrganism.reduceWeight(weightEaten);
                    if(toBeEatenOrganism instanceof Animal) {
                        toBeEatenOrganism.setAlive(false);
                        currentCell.removeOrganism(toBeEatenOrganism);
                    }
                    this.addWeight(weightEaten);
                    break;
                }
            }
        }
    }

    public synchronized void move() {
        if (isAlive()) {
            Cell cellToMove = chooseCellToMove();
            getCurrentCell().removeOrganism(this);
            cellToMove.addOrganism(this);
            setCurrentCell(cellToMove);
        }
    }

    private Cell chooseCellToMove() {
        int randomIndex = getRandomInt(0, getCurrentCell().getNeighborCells().size());
        return getCurrentCell().getNeighborCells().get(randomIndex);
    }

    public synchronized void reproduce() {
        if (this.isAlive()) {
            Cell currentCell = this.getCurrentCell();
            for (Organism organismToReproduce : currentCell.getCellOrganisms()) {
                if (!this.equals(organismToReproduce)
                        && this.getWeight() > this.getLimit().getMaxWeight()/3
                        && currentCell.ifEnoughRoomInCellToReproduce(this)
                        && this.getClass().equals(organismToReproduce.getClass())
                        && organismToReproduce.isAlive()) {
                    OrganismFactory organismFactory = new OrganismFactory();
                    Organism newOrganism = organismFactory.createOrganism(this.getType());
                    currentCell.addOrganism(newOrganism);
                    newOrganism.setCurrentCell(currentCell);
                    break;
                }
            }
        }
    }

    public void action() {
        if (getLimit().getMaxCellAmount() == 0) {
            eat();
            reproduce();
        }
        else {
            for (int i = 0; i < getLimit().getMaxCellMovementPerRound(); i++) {
                move();
                eat();
                reproduce();
            }
        }
        this.reduceWeight(this.getLimit().getMaxWeight() * 0.10);

        if (getWeight() <= 0) {
            this.setAlive(false);
            getCurrentCell().removeOrganism(this);
            getCurrentCell().getGameMap().removeOrganismsFromMap(this);
        }
    }
}
