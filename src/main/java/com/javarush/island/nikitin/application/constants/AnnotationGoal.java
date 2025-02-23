package com.javarush.island.nikitin.application.constants;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectLimitData;
import lombok.Getter;

import java.lang.annotation.Annotation;

/**
 * An enumeration representing the targets of the annotations used in the application.
 * Each enumeration element corresponds to a specific annotation and provides access to its class.
 */

@Getter
public enum AnnotationGoal {
    GAME_UNIT(GameUnit.class),
    INJECT_LIMIT_DATA(InjectLimitData.class);
    private final Class<? extends Annotation> value;

    AnnotationGoal(Class<? extends Annotation> value) {
        this.value = value;
    }
}
