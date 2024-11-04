package borisov.entity.map;

import borisov.entity.Animals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cell {
    public Map<Animals,Integer> cell ;
    private Cell[] cellPosition;
    private Cell[][] CanMove;

    public Cell() {
        this.cell = new HashMap<>();
    }

    public void setCell(Animals animal, Integer count)  {
            if (cell.containsKey(animal)) {
                cell.put(animal, cell.get(animal) + 1);
            }else {cell.put(animal,1);}

    }

    public void removeFromCell(Animals animal) {
        cell.remove(animal);
    }
    private void setCanMove(){

    }

    @Override
    public String toString() {
        for (Map.Entry<Animals,Integer> entry : cell.entrySet()) {
            Animals animal = entry.getKey();
            Integer count = entry.getValue();
            return "{" + animal + "=" + count + "}";


        }
        return "{      }";
    }
}
