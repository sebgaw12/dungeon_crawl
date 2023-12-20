package com.codecool.dungeoncrawl.logic.ui.utils;

/**
 * ID of a graphical representation of a certain tile
 *
 * @param x Horizontal value
 * @param y Vertical value
 */
public record TileId(int x, int y) {
    public static TileId of(int x, int y) {
        return new TileId(x, y);
    }
}
