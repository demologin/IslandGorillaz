package com.javarush.island.kozlov.logic;

import java.util.Map;

public class ProbabilityData {

    private Map<String, Map<String, Integer>> percentages;

    public Map<String, Map<String, Integer>> getPercentages() {
        return percentages;
    }

    public void setPercentages(Map<String, Map<String, Integer>> percentages) {
        this.percentages = percentages;
    }
}
