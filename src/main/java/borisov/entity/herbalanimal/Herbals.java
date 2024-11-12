package borisov.entity.herbalanimal;

import borisov.api.AnimalsFactory;
import borisov.api.MyRandomUtil;
import borisov.config.Action;
import borisov.config.MyConfig;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Herbals implements Animals {
    @Getter
    private final Lock lock = new ReentrantLock();
    private final UUID id;
    @Setter
    @Getter
    protected Cell position;
    @Setter
    protected GameMap map;
    @Getter
    @Setter
    public boolean isAlive;
    @Getter
    protected char simpleName;
    @Getter
    @Setter
    private int weight;
    @Setter
    @Getter
    private Map<String, Integer> chances;
    @Getter
    private int moveSpeed;
    @Setter
    @Getter
    private AnimalsFactory animalsFactory;


    public Herbals() {
        this.id = UUID.randomUUID();
        simpleName = this.getClass().getSimpleName().charAt(0);
    }

    protected Herbals(Herbals original) {
        this.id = UUID.randomUUID();
        this.position = original.position;
        this.map = original.map;
        this.isAlive = original.isAlive;
        this.simpleName = original.simpleName;
        this.weight = original.weight / 2;
        original.setWeight(original.getWeight() / 2);
        this.chances = original.getChances();
        this.moveSpeed = original.getMoveSpeed();
        this.animalsFactory = original.getAnimalsFactory();
        this.animalsFactory.addToMap(this);
        this.position.setAnimalInCell(this);

    }


    protected Cell newPosition(Cell position) {
        Map<Integer, List<Integer>> canMoveXY = position.getCanMoveXY();
        int chooseStep = MyRandomUtil.random(0, canMoveXY.size());
        List<Integer> integers = canMoveXY.get(chooseStep);
        return map.getCell(integers.get(0), integers.get(1));
    }


    @Override
    public void doAction(Action action) {
        switch (action) {
            case MOVE -> move();
            case REPRODUCE -> reproduce();
            case EAT -> eat();
        }
    }

    public void move() {
        Cell nowPosition = position;
        Cell tempPosition = position;
        for (int i = 0; i < this.getMoveSpeed(); i++) {
            tempPosition = newPosition(nowPosition);
            nowPosition = tempPosition;
        }

        Lock lock = tempPosition.getLock();
        lock.lock();
        try {
            position.removeFromCell(this);
            position = tempPosition;
            position.setAnimalInCell(this);
        } finally {
            lock.unlock();
        }


    }

    @Override
    public void eat() {
// трава не реализована поэтому подразумеваем что она всегда есть и травоядные всегда наедаются
        this.setWeight(this.chances.get("fullWeight") + MyConfig.LOOSE_WEIGHT_PER_ROUND);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Herbals herbals = (Herbals) o;
        return Objects.equals(id, herbals.id) && Objects.equals(position, herbals.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
