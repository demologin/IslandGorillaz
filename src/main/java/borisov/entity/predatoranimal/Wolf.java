package borisov.entity.predatoranimal;

import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

public class Wolf extends Predators {


    @Override
    public char getSimpleName() {
        return 0;
    }

    //    @Getter
//    private final char simpleName = this.getClass().getSimpleName().charAt(0);;
//    Cell position;
//    public int weight = 10;
//    GameMap map;
    protected final int moveSpeed = 4;

    @Override
    protected int getMoveSpeed() {
        return this.moveSpeed;
    }

    public Wolf(GameMap map) {
        super(map);
//        this.map = map;
//        simpleName = this.getClass().getSimpleName().charAt(0);
//        int rndHeight = ThreadLocalRandom.current().nextInt(0, map.getHeight());
//        int rndWidth = ThreadLocalRandom.current().nextInt(0, map.getWidth());
//        position = map.getCell(rndWidth, rndHeight);
//        position.setCell(this, 1);
    }


    @Override
    public void eat() {

    }

//    @Override
//    public void move() {
//
//        Cell nowPosition = position;
//        Cell tempPosition =null;
//        for (int i = 0; i < moveSpeed; i++) {
//            tempPosition = newPosition(nowPosition);
//            nowPosition = tempPosition;
//        }
//
//        Lock lock = tempPosition.getLock();
//        lock.lock();
//        try {
//            position.removeFromCell(this);
//            position = tempPosition;
//            position.setCell(this, 1);
//        } finally {
//            lock.unlock();
//        }
//
//    }
//    protected Cell newPosition(Cell position){
//
//        Map<Integer, List<Integer>> canMoveXY = position.getCanMoveXY();
//        int chooseStep = ThreadLocalRandom.current().nextInt(0, canMoveXY.size());
//        List<Integer> integers = canMoveXY.get(chooseStep);
//        System.out.println("step = " + integers.get(0)+ " " + integers.get(1));
//        Cell cell = map.getCell(integers.get(0), integers.get(1));
//        return cell;
//    }


    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return "Wolf";
    }
}
