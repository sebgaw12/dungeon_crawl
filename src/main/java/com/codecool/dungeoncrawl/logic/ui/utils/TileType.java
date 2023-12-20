package com.codecool.dungeoncrawl.logic.ui.utils;

import com.codecool.dungeoncrawl.logic.ui.Renderable;

/**
* Tiles.Tile need 2 parameters in which symbolise a tile from tiles.png
* where first parameter is horizontal and second vertical tile
*/
public enum TileType implements Renderable {
    EMPTY(false, TileId.of(0, 0)),
    FLOOR(true, TileId.of(2, 0)),
    WATER(false, TileId.of(8, 5)),
    WALL(false, TileId.of(10, 17)),
    GRASS(true, TileId.of(5, 0)),
    LETTER_D(true, TileId.of(22, 30)),
    LETTER_O(true, TileId.of(20, 31)),
    LETTER_N(true, TileId.of(19, 31)),
    LETTER_T(true, TileId.of(25, 31)),
    LETTER_G(true, TileId.of(25, 30)),
    LETTER_H(true, TileId.of(26, 30)),
    LETTER_E(true, TileId.of(23, 30)),
    LETTER_R(true, TileId.of(23, 31));

    private final boolean walkable;
    private final TileId tileId;

    TileType(boolean walkable, TileId tileId) {
        this.walkable = walkable;
        this.tileId = tileId;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public TileId getTileId() {
        return tileId;
    }
}
