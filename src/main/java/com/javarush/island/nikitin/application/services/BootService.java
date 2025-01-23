package com.javarush.island.nikitin.application.services;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.constants.ClassPathRegistry;
import com.javarush.island.nikitin.application.util.ClassFinder;
import com.javarush.island.nikitin.domain.entity.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;

import java.util.Set;

public class BootService {
    private final PrototypeService prototypeService;

    public BootService(Settings settings) {
        this.prototypeService = new PrototypeService(settings);
    }

    /**
     * Searches for classes of game units, gets a cache of found classes
     * and creates Biota prototypes based on them
     */

    public void loadComponents() {
        ClassFinder.start(ClassPathRegistry.PATHS_TO_DIRECTORY_CLASSES_UNITS);
        Set<Class<?>> cacheUnitClasses = ClassFinder.getCacheUnitClasses();
        prototypeService.buildBiotaInstancesForEach(cacheUnitClasses);
    }

    public RegistryProto fillRepository() {
        return new RegistryProto(prototypeService.retrieveCacheSnapshot());
    }

    /**
     * Setting additional attributes to prototypes
     *
     * @param navigator - navigator object
     * @param startData - start date of the era report in the game
     */

    public void setAttributesIntoProto(Navigator navigator, int startData) {
        prototypeService.setAttributesIntoProto(navigator, startData);
    }
}
