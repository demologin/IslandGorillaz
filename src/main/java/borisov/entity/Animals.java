package borisov.entity;


import borisov.entity.map.Cell;

public interface Animals extends Reproductable{


    void eat();

    void move();

    char getSimpleName();

    int getWeight();

    void setPosition(Cell cell);

}
