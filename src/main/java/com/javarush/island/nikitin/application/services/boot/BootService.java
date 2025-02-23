package com.javarush.island.nikitin.application.services.boot;

import com.javarush.island.nikitin.application.config.Settings;
import com.javarush.island.nikitin.application.constants.ClassPathRegistry;
import com.javarush.island.nikitin.domain.entity.navigation.Navigator;
import com.javarush.island.nikitin.domain.repository.RegistryProto;

import java.util.HashSet;
import java.util.Set;

public class BootService {
    private static final Set<Class<?>> gameClasses = new HashSet<>();
    private final PrototypeService prototypeService;
    private final ClassFinder classFinder;
    private final DynamicClassGenerator dynamicClass;

    public BootService(Settings settings) {
        this.prototypeService = new PrototypeService(settings);
        this.classFinder = new ClassFinder(gameClasses);
        this.dynamicClass = new DynamicClassGenerator(gameClasses);

    }

    /**
     * Searches for classes of game units, gets a cache of found classes
     * and creates Biota prototypes based on them
     */

    public void loadComponents() {
        classFinder.start(ClassPathRegistry.PATHS_TO_DIRECTORY_CLASSES_UNITS);
        dynamicClass.findDynamicClass(ClassPathRegistry.PATHS_TO_DIRECTORY_DYNAMIC_CLASS);
        prototypeService.buildBiotaInstancesForEach(gameClasses);
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
