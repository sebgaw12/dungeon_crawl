package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Cell {
    @Setter
    @Getter
    private boolean visited = false;
    @Setter
    @Getter
    private TileType tileType;
    @Getter
    @Setter
    private Actor actor;
    @Getter
    @Setter
    private Item item;
    @Getter
    @Setter
    private InteractiveObject interactiveObject;
    @Getter
    private final Position position;

    Cell(Position position, TileType tileType) {
        this.position = position;
        this.tileType = tileType;
    }

    public TileId getVisibleObjectId() {
        if (Objects.nonNull(this.getActor())) {
            return this.getActor().getTileId();
        }
        if (Objects.nonNull(this.getItem())) {
            return this.getItem().getTileId();
        }
        if (Objects.nonNull(this.getInteractiveObject())) {
            return this.getInteractiveObject().getTileId();
        }
        return tileType.getTileId();
    }

    public boolean isWalkable() {
        if (!this.tileType.isWalkable()) {
            return false;
        }
        if (Objects.nonNull(this.getActor())) {
            return false;
        }
        if (Objects.nonNull(this.getInteractiveObject()) && !this.getInteractiveObject().isWalkable()) {
            return false;
        }
        return true;
    }

    public boolean isVisible(GameMap gameMap) {
        int playerX = gameMap.getPlayer().getPosition().x();
        int playerY = gameMap.getPlayer().getPosition().y();
        int cellX = position.x();
        int cellY = position.y();

        int distance = Math.abs(playerX - cellX) + Math.abs(playerY - cellY);
        int fieldOfView = gameMap.getPlayer().getFieldOfViewDistance();
        return distance <= fieldOfView;
    }

    public Cell getNeighbor(Position nextPosition, GameMap gameMap) {
        return gameMap.getCell(Position.of(position.x() + nextPosition.x(), position.y() + nextPosition.y()));
    }

}
