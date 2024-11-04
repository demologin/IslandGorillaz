package borisov.entity.predatoranimal;

import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf implements Animals{
    Cell position;
    public int weight = 10;
    GameMap map;
    private int moveSpeed = 3;

    public Wolf(GameMap map) {
        this.map = map;
        int rndHeight = ThreadLocalRandom.current().nextInt(0, map.getHeight());
        int rndWidth = ThreadLocalRandom.current().nextInt(0, map.getWidth());
        position = map.getCell(rndWidth,rndHeight );
        position.setCell(this, 1);
    }


    @Override
    public void eat() {

    }

    @Override
    public void move() {
        int rndHeight = ThreadLocalRandom.current().nextInt(0, map.getHeight());
        int rndWidth = ThreadLocalRandom.current().nextInt(0, map.getWidth());
        position.removeFromCell(this);
        position = map.getCell(rndWidth,rndHeight );
        position.setCell(this, 1);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return "Wolf";
    }
}
