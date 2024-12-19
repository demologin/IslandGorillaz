package com.javarush.island.nikitin.application.config;

import com.javarush.island.nikitin.domain.api.GameUnit;
import com.javarush.island.nikitin.domain.api.InjectProps;
import lombok.Getter;

import java.lang.annotation.Annotation;

public enum AnnotationGoal {
    GAME_UNIT(GameUnit.class),
    INJECT_PROPS(InjectProps.class);
    @Getter
    private final Class<? extends Annotation> value;

    AnnotationGoal(Class<? extends Annotation> value) {
        this.value = value;
    }
}
