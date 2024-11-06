package borisov.entity.predatoranimal;


import borisov.entity.map.GameMap;





public class Wolf extends Predators {


    @Override
    public char getSimpleName() {
        return simpleName;
    }


    protected final int moveSpeed = 4;

    @Override
    protected int getMoveSpeed() {
        return this.moveSpeed;
    }

    public Wolf(GameMap map) {
        super(map);

    }


    @Override
    public void eat() {

    }




    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return String.valueOf(simpleName);
    }
}
