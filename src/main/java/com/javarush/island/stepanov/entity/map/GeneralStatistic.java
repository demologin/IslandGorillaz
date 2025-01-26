package com.javarush.island.stepanov.entity.map;

import com.javarush.island.stepanov.config.Setting;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.stepanov.constants.Constants.MIN_NUMBER_OF_ORGANISMS;

public class GeneralStatistic {
    private final Map<String, AtomicInteger> map;

    public GeneralStatistic() {
        map = new ConcurrentHashMap<>();
        Map<String, String> organismsMap = Setting.ORGANISMS_VIEW_MAP;
        organismsMap.forEach((k, v) -> {
            map.put(k, new AtomicInteger(MIN_NUMBER_OF_ORGANISMS));
        });
    }

    public void clear() {
        map.forEach((k, v) -> v.set(MIN_NUMBER_OF_ORGANISMS));
    }

    public Map<String, AtomicInteger> getMap() {
        return map;
    }

    public void addValue(String key, int value) {
        AtomicInteger currentValue = map.get(key);
        if (currentValue != null) {
            currentValue.addAndGet(value);
        }
    }
}
