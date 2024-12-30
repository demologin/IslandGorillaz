package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.constants.ClassPathRegistry;
import com.javarush.island.nikitin.application.util.ClassFinder;
import com.javarush.island.nikitin.domain.entity.map.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;

import java.util.Set;

public class BootService {
    private final PrototypeService prototypeService;

    public BootService(PrototypeService prototypeService) {
        this.prototypeService = prototypeService;
    }

    public void loadComponents() {
        ClassFinder.start(ClassPathRegistry.PATHS_TO_DIRECTORY_CLASSES_UNITS);
        Set<Class<?>> cacheUnitClasses = ClassFinder.getCacheUnitClasses();
        prototypeService.buildBiotaInstancesForEach(cacheUnitClasses);
    }

    public RegistryProto fillRepository() {
        return new RegistryProto(prototypeService.flush());
    }

    public void setAttributesIntoProto(Navigator navigator, int startData) {
        prototypeService.setAttributesIntoProto(navigator, startData);
    }
}
