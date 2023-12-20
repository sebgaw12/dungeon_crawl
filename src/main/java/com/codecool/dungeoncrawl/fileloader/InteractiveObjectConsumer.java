package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Chest;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Door;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Boat;

public class InteractiveObjectConsumer {

    private InteractiveObjectConsumer() {
    }

    public static void door(Cell cell) {
        cell.setInteractiveObject(new Door(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void chest(Cell cell) {
        cell.setInteractiveObject(new Chest(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void boat(Cell cell) {
        cell.setInteractiveObject(new Boat(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }
}
