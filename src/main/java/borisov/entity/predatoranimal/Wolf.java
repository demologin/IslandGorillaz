package borisov.entity.predatoranimal;

import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import lombok.Getter;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

public class Wolf extends Predators {


    @Getter
    private final char simpleName;
    Cell position;
    public int weight = 10;
    GameMap map;
    private final int moveSpeed = 4;

    public Wolf(GameMap map) {

        this.map = map;
        simpleName = this.getClass().getSimpleName().charAt(0);
        int rndHeight = ThreadLocalRandom.current().nextInt(0, map.getHeight());
        int rndWidth = ThreadLocalRandom.current().nextInt(0, map.getWidth());
        position = map.getCell(rndWidth, rndHeight);
        position.setCell(this, 1);
    }


    @Override
    public void eat() {

    }

    @Override
    public void move() {
        Map<Integer, List<Integer>> canMoveXY = position.getCanMoveXY();
        int chooseStep = ThreadLocalRandom.current().nextInt(0, canMoveXY.size());
        List<Integer> integers = canMoveXY.get(chooseStep);


        Lock lock = position.getLock();
        lock.lock();
        try {
            position.removeFromCell(this);
            position = map.getCell(integers.get(0), integers.get(1));
            position.setCell(this, 1);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return "Wolf";
    }
}
