package com.javarush.island.nikitin.application.constants;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.domain.entity.biota.LimitData;
import com.javarush.island.nikitin.domain.entity.biota.PreferenceMenu;
import com.javarush.island.nikitin.domain.entity.biota.Property;
import lombok.Getter;

/**
 * An enumeration that represents a list of classes used in the application.
 * Each enumeration element corresponds to a specific class and provides access to its object.
 */

@Getter
public enum ClazzList {
    PREFERENCE_MENU(PreferenceMenu.class),
    PROPERTY(Property.class),
    LIMIT_DATA(LimitData.class),
    SETTING(Settings.class);

    private final Class<?> clazz;

    ClazzList(Class<?> clazz) {
        this.clazz = clazz;
    }
}
