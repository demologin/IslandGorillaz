package com.javarush.island.stepanov.config;

import com.javarush.island.stepanov.entity.oganism.Organism;
import com.javarush.island.stepanov.util.YamalUtil;
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

}
