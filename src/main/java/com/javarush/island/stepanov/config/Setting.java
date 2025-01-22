package com.javarush.island.stepanov.config;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.services.AnimalService;
import com.javarush.island.stepanov.services.PlantService;
import com.javarush.island.stepanov.util.YamalUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    private static final String SETTING_YAMAL = "stepanov/setting.yaml";
    public static final AnimalService[] PROTOTYPES_ANIMALS = EntityScanner.createAnimalsPrototypes();
    public static final PlantService[] PROTOTYPES_PLANTS= EntityScanner.createPlantsPrototypes();
    public static final Map<String,String> ORGANISMS_VIEW_MAP =
            EntityScanner.createOrganismsViewMap(PROTOTYPES_ANIMALS,PROTOTYPES_PLANTS);
    private static volatile Setting SETTING;

    private Setting() {
        YamalUtil.loadFromYaml(this,SETTING_YAMAL);
    }

    public static Setting get() {
        Setting setting = SETTING;
        if (Objects.isNull(setting)) {
            synchronized (Setting.class) {
                if (SETTING == null) {
                    setting = new Setting();
                }
            }
        }
        return setting;
    }

    private int stepDelay;
    private int rows;
    private int cols;
    private int turns;
    private int occupancyRate;
    private int minOfFlocks;
    private double minWeight;
    @Getter(AccessLevel.PROTECTED)
    private Map<String, Map<String, Integer>> foodMap = new LinkedHashMap<>();

    public Map<String, Integer> getFoodMap(String keyName) {
        this.foodMap.putIfAbsent(keyName, new LinkedHashMap<>());
        return foodMap.get(keyName);
    }

}
