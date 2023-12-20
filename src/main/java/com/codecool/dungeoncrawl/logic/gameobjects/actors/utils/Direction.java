package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;

public enum Direction {
    UP(Position.of(0, -1)),
    DOWN(Position.of(0, 1)),
    LEFT(Position.of(-1, 0)),
    RIGHT(Position.of(1, 0));

    private final Position position;

    Direction(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
