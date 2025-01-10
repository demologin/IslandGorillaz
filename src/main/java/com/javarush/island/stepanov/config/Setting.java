package com.javarush.island.stepanov.config;

import com.javarush.island.stepanov.entity.oganism.Organism;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    private static final String SETTING_YAMAL = "stepanov/setting.yaml";
    //    private static final String[] TIPES = new String[]{"Horse"};
    public static final Organism[] PROTOTYPES= EntityScanner.createPrototypes();
    private static volatile Setting SETTING;

    private Setting() {
        loadFromYaml();
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

    private int period = 1000;
    private int rows = 2;
    private int cols = 2;
    private int count = 2;

    private void loadFromYaml() {
    }

}
