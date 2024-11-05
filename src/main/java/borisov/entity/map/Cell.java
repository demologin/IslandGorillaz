package borisov.entity.map;

import borisov.config.AnimalsList;
import borisov.config.MyConfig;
import borisov.entity.Animals;
import lombok.Getter;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cell {
    public List<Animals> cell;
    private final int cellPositionX;
    private final int cellPositionY;
    @Getter
    private final Lock lock = new ReentrantLock();

    @Getter
    private final Map<Integer, List<Integer>> canMoveXY = new HashMap<>();
    private final int allAnimals;



    public Cell(int x, int y) {
        this.cellPositionX = x;
        this.cellPositionY = y;
        this.cell = new CopyOnWriteArrayList<>();
        allAnimals = AnimalsList.values().length;
        setCanMoveXY();
        System.out.println(canMoveXY.toString());
    }

    public void setCell(Animals animal, Integer count) {
        cell.add(animal);


    }

    public void removeFromCell(Animals animal) {
        cell.remove(animal);
    }

    private void setCanMoveXY() {
        int row = MyConfig.MAP_WIDTH;
        int col = MyConfig.MAP_HEIGHT;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};


        for (int i = 0; i < 4; i++) {
            int newX = cellPositionX + dx[i];
            int newY = cellPositionY + dy[i];
            int size = canMoveXY.size();

            if (newX >= 0 && newX < row && newY >= 0 && newY < col) {
                canMoveXY.computeIfAbsent(size, k -> new ArrayList<>()).addAll(List.of(newX, newY));
            }
        }

    }


@Override
public String toString() {
    if (!cell.isEmpty()) {
        Map<Character, Integer> repeat = new HashMap<>();

        for (Animals animal : cell) {
            if (!repeat.containsKey(animal.getSimpleName())) {
                repeat.put(animal.getSimpleName(), 1);
            } else {
                repeat.put(animal.getSimpleName(), repeat.get(animal.getSimpleName()) + 1);
            }

        }
        StringBuilder s = new StringBuilder();
        s.append("{");
        repeat.forEach((key, value) -> s.append(key + "=" + value));

        int cellAnimals = repeat.size();


        if (cellAnimals < allAnimals) {
            s.append(" ".repeat(3));
            s.append("}");

        } else {
            s.append("}");
        }

        return s.toString();
    } else return "{" + " ".repeat(allAnimals * 3) + "}";
}
}
