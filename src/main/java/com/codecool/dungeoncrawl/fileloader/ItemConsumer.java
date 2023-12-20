package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;

public class ItemConsumer {
    private ItemConsumer() {

    }

    public static void food(Cell cell) {
        cell.setItem(new Food(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void torch(Cell cell) {
        cell.setItem(new Torch(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void sword(Cell cell) {
        cell.setItem(new Sword(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void armor(Cell cell) {
        cell.setItem(new Armor(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void key(Cell cell) {
        cell.setItem(new Key(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

}
