package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.Renderable;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import lombok.Getter;
import lombok.Setter;

public abstract class Actor implements Renderable {
    @Getter
    @Setter
    private TileId tileId;
    @Getter
    @Setter
    private int health = 20;
    @Getter
    @Setter
    private int defense = 1;
    @Getter
    @Setter
    private int attack = 3;
    @Getter
    @Setter
    private Position position;
    @Getter
    @Setter
    private int fieldOfViewDistance = 7;

    public Actor(TileId tileId, Position position) {
        this.tileId = tileId;
        this.position = position;
    }

    public boolean isDead() {
        return health <= 0;
    }

}
