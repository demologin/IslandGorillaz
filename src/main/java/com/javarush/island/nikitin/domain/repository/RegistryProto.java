package com.javarush.island.nikitin.domain.repository;

import com.javarush.island.nikitin.domain.entity.biota.Biota;

import java.util.HashMap;
import java.util.Map;

public record RegistryProto(Map<String, Biota> registry) {

    @Override
    public Map<String, Biota> registry() {
        return new HashMap<>(registry);
    }

    public Biota getByName(String nameCommunity) {
        return registry.get(nameCommunity);
    }

}
