package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;

public class EnvironmentalObjectConsumer {
    private EnvironmentalObjectConsumer() {
    }

    public static void empty(Cell cell) {
        cell.setTileType(TileType.EMPTY);
    }

    public static void floor(Cell cell) {
        cell.setTileType(TileType.FLOOR);
    }

    public static void wall(Cell cell) {
        cell.setTileType(TileType.WALL);
    }

    public static void water(Cell cell) {
        cell.setTileType(TileType.WATER);
    }

    public static void grass(Cell cell) {
        cell.setTileType(TileType.GRASS);
    }

    public static void letterD(Cell cell) {
        cell.setTileType(TileType.LETTER_D);
    }

    public static void letterO(Cell cell) {
        cell.setTileType(TileType.LETTER_O);
    }

    public static void letterN(Cell cell) {
        cell.setTileType(TileType.LETTER_N);
    }

    public static void letterT(Cell cell) {
        cell.setTileType(TileType.LETTER_T);
    }

    public static void letterG(Cell cell) {
        cell.setTileType(TileType.LETTER_G);
    }

    public static void letterH(Cell cell) {
        cell.setTileType(TileType.LETTER_H);
    }

    public static void letterE(Cell cell) {
        cell.setTileType(TileType.LETTER_E);
    }

    public static void letterR(Cell cell) {
        cell.setTileType(TileType.LETTER_R);
    }
}
