package borisov.entity.predatoranimal;

import borisov.api.MyRandomUtil;
import borisov.entity.Animals;
import borisov.entity.map.Cell;
import borisov.entity.map.GameMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Predators implements Animals {
    private final UUID id;
    @Setter
    protected Cell position;
    @Setter
    protected GameMap map;
    protected char simpleName;


    public Predators() {
        this.id = UUID.randomUUID();
        simpleName = this.getClass().getSimpleName().charAt(0);
    }


    public abstract int getWeight();
    protected abstract void setWeight(int height);

    protected Cell newPosition(Cell position) {
        Map<Integer, List<Integer>> canMoveXY = position.getCanMoveXY();
        int chooseStep = MyRandomUtil.random(0, canMoveXY.size());
        List<Integer> integers = canMoveXY.get(chooseStep);
        Cell cell = map.getCell(integers.get(0), integers.get(1));
        return cell;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predators predators = (Predators) o;
        return
                Objects.equals(id, predators.id) &&
                        Objects.equals(position, predators.position) &&
                        Objects.equals(map, predators.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        setWeight(getWeight() - 1);

    }
}
