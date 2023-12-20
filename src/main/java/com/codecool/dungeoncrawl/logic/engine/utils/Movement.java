package com.codecool.dungeoncrawl.logic.engine.utils;

/**
 * Record that allow actors to "Move" from their current position to new position
 *
 * @param currentPosition
 * @param newPosition
 */
public record Movement(Position currentPosition, Position newPosition) {
    public static Movement of(Position currentPosition, Position newPosition) {
        return new Movement(currentPosition, newPosition);
    }
}
