package com.javarush.island.nikitin.domain.repository;

import com.javarush.island.nikitin.domain.entity.biota.Biota;

import java.util.Map;

public record RegistryProto(Map<String, Biota> registry) {
}
