package com.javarush.island.stepanov.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    private static final String SETTING_YAMAL = "stepanov/setting.yaml";
//    private static final String[] TIPES = new String[]{"Horse"};
//    public static final Organism[] = new Organism[]{new Organism()};
    private static volatile Setting SETTING;

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

    private int period=1000;
    private int rows = 3;
    private int cols = 3;
    
    private Setting() {
        loadFromYaml();
    }

    private void loadFromYaml() {
    }

}
