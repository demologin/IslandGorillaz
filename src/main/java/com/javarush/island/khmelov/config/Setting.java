package com.javarush.island.khmelov.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.island.khmelov.entity.organizm.Organism;
import com.javarush.island.khmelov.entity.organizm.animals.herbivores.Deer;
import com.javarush.island.khmelov.entity.organizm.animals.herbivores.Horse;
import com.javarush.island.khmelov.entity.organizm.animals.herbivores.Mouse;
import com.javarush.island.khmelov.entity.organizm.animals.herbivores.Rabbit;
import com.javarush.island.khmelov.entity.organizm.animals.predators.Bear;
import com.javarush.island.khmelov.entity.organizm.animals.predators.Wolf;
import com.javarush.island.khmelov.entity.organizm.plants.Grass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    public static final String SETTING_YAML = "khmelov/setting.yaml";
    private static final Class<?>[] TYPES = {
            Wolf.class, Bear.class,
            Horse.class, Mouse.class, Deer.class, Rabbit.class,
            Grass.class,};
    public static final Organism[] PROTOTYPES = EntityScanner.createPrototypes(TYPES);

    //======================== SAFE_THREAD_SINGLETON =============================
    private static volatile Setting SETTING;

    public static Setting get() {
        Setting setting = SETTING;
        if (Objects.isNull(setting)) {
            synchronized (Setting.class) {
                if (Objects.isNull(setting = SETTING)) {
                    setting = SETTING = new Setting();
                }
            }
        }
        return setting;
    }
    //======================== /SAFE_THREAD_SINGLETON =============================


    //=============================== DATA ========================================

    private int period;
    private int rows;
    private int cols;

    private int showRows;
    private int showCols;
    private int consoleCellWith;
    private int percentAnimalSlim;
    private int percentPlantGrow;
    @Getter(AccessLevel.PROTECTED)
    private Map<String, Map<String, Integer>> foodMap = new LinkedHashMap<>();

    public Map<String, Integer> getFoodMap(String keyName) {
        this.foodMap.putIfAbsent(keyName, new LinkedHashMap<>());
        return foodMap.get(keyName);
    }
    //=============================== /DATA ========================================

    //================================ INIT ========================================

    private Setting() {
        loadFromDefault();
        updateFromYaml();
    }

    private void loadFromDefault() {
        period = Default.PERIOD;

        rows = Default.ROWS;
        cols = Default.COLS;

        showRows = Default.SHOW_ROWS;
        showCols = Default.SHOW_COLS;
        consoleCellWith = Default.CONSOLE_CELL_WITH;
        percentAnimalSlim = Default.PERCENT_ANIMAL_SLIM;
        percentPlantGrow = Default.PERCENT_PLANT_GROW;
        for (int i = 0, n = Default.names.length; i < n; i++) {
            String key = Default.names[i];
            this.foodMap.putIfAbsent(key, new LinkedHashMap<>());
            for (int j = 0; j < n; j++) {
                int ratio = Default.setProbablyTable[i][j];
                if (ratio > 0) {
                    this.foodMap.get(key).put(Default.names[j], ratio);
                }
            }
        }
    }

    @SneakyThrows
    private void updateFromYaml() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectReader readerForUpdating = mapper.readerForUpdating(this);
        URL resource = Setting.class.getClassLoader().getResource(SETTING_YAML);
        if (Objects.nonNull(resource)) {
            readerForUpdating.readValue(resource.openStream());
        }
    }
    //=============================== /INIT ========================================

    //=============================== FOR DEBUG ONLY ===============================
    @Override
    public String toString() {
        ObjectMapper yaml = new ObjectMapper(new YAMLFactory());
        yaml.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return yaml.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    //=============================== /FOR DEBUG ONLY===============================

}
