package com.javarush.island.nikitin.domain.entity.navigation;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

/**
 * An enumeration representing the possibility of moving direction.
 * Each direction is associated with a specific movement strategy,
 * implementing the {@link MoveStrategy} interface
 */

@Getter
public enum Direction {
    NORTH(new MoveNorth()),
    SOUTH(new MoveSouth()),
    WEST(new MoveWest()),
    EAST(new MoveEast());

    private final MoveStrategy strategy;

    Direction(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public static Direction getRandomDirection() {
        int amountDirection = Direction.values().length;
        int numberRandomDirection = ThreadLocalRandom.current().nextInt(amountDirection);
        return Direction.values()[numberRandomDirection];
    }
}
