package com.javarush.island.nikitin.domain.entity.map.navigation;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    NORTH(new MoveNorth()),
    SOUTH(new MoveSouth()),
    WEST(new MoveWest()),
    EAST(new MoveEast());

    @Getter
    private final MoveStrategy strategy;

    Direction(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public static Direction getRandomDirection(){
        int amountDirection = Direction.values().length;
        int numberRandomDirection = ThreadLocalRandom.current().nextInt(amountDirection);
        return Direction.values()[numberRandomDirection];
    }
}
