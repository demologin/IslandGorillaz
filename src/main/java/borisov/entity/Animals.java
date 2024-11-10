package borisov.entity;


import borisov.config.Action;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;

import java.util.Map;
import java.util.concurrent.locks.Lock;

public interface Animals extends Reproductable{
    Cell getPosition();

    Lock getLock();

    void eat();

    void move();

    char getSimpleName();

    int getWeight();

    void setWeight(int weight);

    void setPosition(Cell cell);

    void setMap(GameMap map);

    int getMoveSpeed();

    void setChances(Map<String,Integer> chances);

    Map<String,Integer> getChances();

    boolean isAlive();

    void setAlive(boolean alive);

    void doAction(Action action);



}
