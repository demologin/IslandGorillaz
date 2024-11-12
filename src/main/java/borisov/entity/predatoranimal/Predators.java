package borisov.entity.predatoranimal;

import borisov.api.AnimalsFactory;
import borisov.api.MyRandomUtil;
import borisov.config.Action;
import borisov.config.MyConfig;
import borisov.entity.Animals;
import borisov.entity.herbalanimal.Herbals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Predators implements Animals {
    @Getter
    private Lock lock = new ReentrantLock();
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


    public Predators() {
        this.id = UUID.randomUUID();
        simpleName = this.getClass().getSimpleName().charAt(0);
    }

    protected Predators(Predators original) {
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
        Cell cell = map.getCell(integers.get(0), integers.get(1));
        return cell;
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

        lock = tempPosition.getLock();
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
        try {
// планировалось брать лок на выбранном животном, но что-то пошло не так, отлаживать не стал, взял лок на ячейке
            lock = position.getLock();
            lock.lock();
            try {
                Map<Class<? extends Animals>, Set<Animals>> animalsInCell = position.getAnimalsInCell();
                Animals target =
                        animalsInCell.entrySet().stream()
                                .filter(k -> Herbals.class.isAssignableFrom(k.getKey()))
                                .filter(k -> MyRandomUtil.randomPercent(this.chances.get(k.getKey().getSimpleName())))
                                .map(k -> k.getValue()
                                        .stream()
                                        .findFirst().orElse(null))
                                .filter(Objects::nonNull)
                                .findFirst().orElse(null);
                if (target != null) {


                    if (this.chances.get("toEatUp") >= target.getWeight()) {
                        this.setWeight(this.getWeight() + target.getWeight() + MyConfig.LOOSE_WEIGHT_PER_ROUND);
                        target.setWeight(0);

                    } else {
                        target.setWeight(target.getWeight() - this.chances.get("toEatUp"));

                        this.setWeight(this.chances.get("fullWeight") + MyConfig.LOOSE_WEIGHT_PER_ROUND);
                    }

                }
            } finally {
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predators predators = (Predators) o;
        return
                Objects.equals(id, predators.id) &&
                        Objects.equals(position, predators.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
