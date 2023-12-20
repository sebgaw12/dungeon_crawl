package com.codecool.dungeoncrawl.logic.gameobjects.items.utils;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;

public enum ItemTileId {
    ARMOR(TileId.of(0, 23)),
    FOOD(TileId.of(15, 29)),
    KEY(TileId.of(16, 23)),
    SWORD(TileId.of(2, 28)),
    TORCH(TileId.of(4, 15));


    private final TileId tileId;

    ItemTileId(TileId tileId) {
        this.tileId = tileId;
    }

    public TileId getTileId() {
        return tileId;
    }
}
