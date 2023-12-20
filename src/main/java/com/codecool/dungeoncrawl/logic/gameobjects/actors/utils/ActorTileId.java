package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;

public enum ActorTileId {
    OGRE(TileId.of(30, 6)),
    SKELETON(TileId.of(29, 6)),
    ARCHER(TileId.of(28, 1)),
    ARCHMAGE(TileId.of(24, 2)),
    PALADIN(TileId.of(28, 0)),
    WARRIOR(TileId.of(30, 0)),
    MAGE(TileId.of(24, 6));

    private final TileId tileId;

    ActorTileId(TileId tileId) {
        this.tileId = tileId;
    }

    public TileId getTileId() {
        return tileId;
    }
}
