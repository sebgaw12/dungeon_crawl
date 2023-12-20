package com.codecool.dungeoncrawl.logic.ui;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tiles {
    private static final String FILE_WITH_TILES = "/tiles.png";
    private static final String FILE_WITH_DARK_TILES = "/dark_tiles.png";
    private static final Image tileSet = new Image(FILE_WITH_TILES, 543 * 2, 543 * 2, true, false);
    private static final Image darkenTileSet = new Image(FILE_WITH_DARK_TILES, 543 * 2, 543 * 2, true, false);
    public static int TILE_SIZE = 32;

    public static class Tile {
        public final int x, y, w, h;

        Tile(TileId tileId) {
            x = tileId.x() * (TILE_SIZE + 2);
            y = tileId.y() * (TILE_SIZE + 2);
            w = TILE_SIZE;
            h = TILE_SIZE;
        }
    }

    public static void drawTile(GraphicsContext context, TileId tileId, Position position) {
        Tile tile = new Tile(tileId);
        context.drawImage(tileSet, tile.x, tile.y, tile.w, tile.h,
                position.x() * TILE_SIZE, position.y() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static void drawTile(GraphicsContext context, TileId tileId, Position position, int tileSize) {
        Tile tile = new Tile(tileId);
        context.drawImage(tileSet, tile.x, tile.y, tile.w, tile.h,
                position.x() * tileSize, position.y() * tileSize, tileSize, tileSize);
    }

    public static void drawHiddenTile(GraphicsContext context, Position position) {
        context.setFill(Color.BLACK);
        context.fillRect(position.x() * TILE_SIZE, position.y() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static void drawVisitedTile(GraphicsContext context, TileId tileId, Position position) {
        Tile tile = new Tile(tileId);
        context.drawImage(darkenTileSet, tile.x, tile.y, tile.w, tile.h,
                position.x() * TILE_SIZE, position.y() * TILE_SIZE, TILE_SIZE, TILE_SIZE);

    }

}
