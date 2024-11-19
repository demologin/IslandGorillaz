package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.config.ClassFinder;
import com.javarush.island.nikitin.application.constants.ClassPathRegistry;
import com.javarush.island.nikitin.domain.api.InjectProps;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;
import com.javarush.island.nikitin.application.config.PrototypeCreator;
import lombok.Getter;

public class BootService {
    private final PrototypeCreator prototypeCreator;
    private final ClassFinder classFinder;
    @Getter
    private RegistryProto repository;

    public BootService() {
        this.prototypeCreator = new PrototypeCreator(InjectProps.class);
        this.classFinder = new ClassFinder();
    }

    public void load() {
        classFinder.start(ClassPathRegistry.PATHS_TO_DIRECTORY_CLASSES_UNITS);
        prototypeCreator.configure(classFinder.getCashUnitClasses());
    }

    public void fillRepository() {
        repository = new RegistryProto(prototypeCreator.flush());
    }

    public void installNavigatorInProto(Navigator navigator) {
        prototypeCreator.setNavigatorIntoProto(navigator);
    }
}
