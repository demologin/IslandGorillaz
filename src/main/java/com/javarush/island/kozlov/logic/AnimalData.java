package com.javarush.island.kozlov.logic;

import java.util.Map;

public class AnimalData {
    private int maxOnCell;
    private Map<String, Integer> prey;

    public int getMaxOnCell() {
        return maxOnCell;
    }

    public void setMaxOnCell(int maxOnCell) {
        this.maxOnCell = maxOnCell;
    }

    public Map<String, Integer> getPrey() {
        return prey;
    }

    public void setPrey(Map<String, Integer> prey) {
        this.prey = prey;
    }
}
