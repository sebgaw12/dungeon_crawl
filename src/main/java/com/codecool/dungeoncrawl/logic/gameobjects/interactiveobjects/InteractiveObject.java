package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.Renderable;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import lombok.Getter;
import lombok.Setter;

public abstract class InteractiveObject implements Renderable {
    @Getter
    @Setter
    private TileId tileId;
    @Getter
    private Position position;

    public InteractiveObject(TileId tileId, Position position) {
        this.tileId = tileId;
        this.position = position;
    }
    public abstract void interact();
    public abstract boolean isWalkable();

}
