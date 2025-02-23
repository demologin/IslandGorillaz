package com.javarush.island.khmelov.entity.map;

import com.javarush.island.khmelov.entity.organizm.Organisms;
import com.javarush.island.khmelov.util.Rnd;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ResidentMap extends LinkedHashMap<String, Organisms> {
    private static final int PERCENT_RANDOM_ROTATE = 1;

    private void checkNull(Object key) {
        this.putIfAbsent(key.toString(), new Organisms());
    }

    @Override
    public Organisms get(Object key) {
        checkNull(key);
        return super.get(key);
    }

    @Override
    public Organisms put(String key, Organisms value) {
        checkNull(key);
        return super.put(key, value);
    }

    public void randomRotateResidents() {
        if (size() > 1 && Rnd.get(PERCENT_RANDOM_ROTATE)) {
            synchronized (this) {
                Set<Map.Entry<String, Organisms>> entrySet = entrySet();
                var iterator = entrySet.iterator();
                if (iterator.hasNext()) {
                    var organisms = iterator.next();
                    iterator.remove();
                    put(organisms.getKey(), organisms.getValue());
                }
            }
        }
    }
}
