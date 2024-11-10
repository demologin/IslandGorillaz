package borisov.entity;


import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;

import java.util.Map;

public interface Animals extends Reproductable{


    void eat();

    void move();

    char getSimpleName();

    int getWeight();

    void setPosition(Cell cell);

    void setMap(GameMap map);

    int getMoveSpeed();

    void setChances(Map<String,Integer> chances);

    Map<String,Integer> getChances();

    boolean isAlive();



}
