package com.javarush.island.stepanov.entity.map;

import com.javarush.island.stepanov.config.Setting;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.island.stepanov.constants.Constants.MIN_NUMBER_OF_ORGANISMS;

public class GeneralStatisticsMap {
    private final Map<String, Integer> map =new HashMap<>();

    public GeneralStatisticsMap() {
        Map<String, String> organismsMap = Setting.ORGANISMS_VIEW_MAP;
        organismsMap.forEach((k, v)->{
            map.put(k,MIN_NUMBER_OF_ORGANISMS);
        });
    }

    public void clear() {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            entry.setValue(MIN_NUMBER_OF_ORGANISMS); // Устанавливаем значение 0 для каждого ключа
        }
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void addValue(String key, int value) {
        Integer currentvalue = map.get(key);
        currentvalue = currentvalue + value;
        map.put(key, currentvalue);
    }
}
