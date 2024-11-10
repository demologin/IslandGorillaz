package borisov.entity.map;

import borisov.config.AnimalsList;
import borisov.config.MyConfig;
import borisov.entity.Animals;
import lombok.Getter;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Cell {
    public Map<Class<? extends Animals>, Set<Animals>> animalsInCell;
    private final int cellPositionX;
    private final int cellPositionY;
    private final int allAnimalsCountOnMap;
    private final String emptyCellPrint;
    @Getter
    private final Lock lock = new ReentrantLock();

    @Getter
    private final Map<Integer, List<Integer>> canMoveXY = new HashMap<>();


    public Cell(int x, int y) {
        this.cellPositionX = x;
        this.cellPositionY = y;
        this.animalsInCell = new ConcurrentHashMap<>();
        allAnimalsCountOnMap = AnimalsList.values().length;
        emptyCellPrint = getCellPrint();
        setCanMoveXY();

    }

    public void setAnimalInCell(Animals animal) {
        animalsInCell.computeIfAbsent(animal.getClass(), k -> new HashSet<>()).add(animal);


    }

    public void removeFromCell(Animals animal) {
        Set<Animals> tempAnimals = animalsInCell.get(animal.getClass());
        tempAnimals.remove(animal);
        if (tempAnimals.isEmpty()) {
            animalsInCell.remove(animal.getClass());
        }
    }

    private void setCanMoveXY() {
        int row = MyConfig.MAP_WIDTH;
        int col = MyConfig.MAP_HEIGHT;
        //possible steps
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


    private String getCellPrint() {
        final String emptyCellPrint;
        emptyCellPrint = "{" + " ".repeat(allAnimalsCountOnMap * 3) + "}";
        return emptyCellPrint;
    }

    @Override
    public String toString() {
        if (!animalsInCell.isEmpty()) {
            StringBuilder s = new StringBuilder();
            s.append("{");

            animalsInCell.forEach((key, value) -> s.append(key.getSimpleName().charAt(0)).append("=").append(value.size()));


            if (animalsInCell.size() < allAnimalsCountOnMap) {

                s.append(" ".repeat(3));
                s.append("}");

            } else {

                s.append("}");
            }


            return s.toString();
        } else return emptyCellPrint;
    }
}
