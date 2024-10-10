package com.javarush.island.levchuk.entities;

import java.util.Map;

public class Animal extends Entity {
    private double weight;
    private double weightDefault;
    private double weightSaturation;
    private int speedMax;
    private Map<String, Integer> likelyFood;
}
