package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Boat;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Chest;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Door;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.InteractiveObjectModel;

public class InteractiveObjectLoaderConsumer {
    public static void door(BaseModel baseModel, GameMap gameMap) {

        InteractiveObjectModel interactiveObjectModel = (InteractiveObjectModel) baseModel;
        Cell cell = gameMap.getCell(interactiveObjectModel.getPosition());
        Door door = new Door(Position.of(cell.getPosition().x(), cell.getPosition().y()));
        if (interactiveObjectModel.isInteracted()) {
            door.setOpen(true);
            door.setLocked(false);
        }
        cell.setInteractiveObject(door);
        gameMap.addToGameObjectList(cell);
    }

    public static void chest(BaseModel baseModel, GameMap gameMap) {
        InteractiveObjectModel interactiveObjectModel = (InteractiveObjectModel) baseModel;
        Cell cell = gameMap.getCell(interactiveObjectModel.getPosition());
        Chest chest = new Chest(Position.of(cell.getPosition().x(), cell.getPosition().y()));
        if (interactiveObjectModel.isInteracted()) {
            chest.setOpen();
        }
        cell.setInteractiveObject(chest);
        gameMap.addToGameObjectList(cell);
    }

    public static void boat(BaseModel baseModel, GameMap gameMap) {
        Cell cell = gameMap.getCell(baseModel.getPosition());
        cell.setInteractiveObject(new Boat(Position.of(cell.getPosition().x(), cell.getPosition().y())));
        gameMap.addToGameObjectList(cell);
    }
}
