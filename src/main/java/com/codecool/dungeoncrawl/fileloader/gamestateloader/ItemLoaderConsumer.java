package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.ItemModel;

public class ItemLoaderConsumer {
    private ItemLoaderConsumer() {
    }

    public static void food(BaseModel baseModel, GameMap gameMap) {
        Cell cell = gameMap.getCell(baseModel.getPosition());
        cell.setItem(new Food(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
        gameMap.addToGameObjectList(cell);
    }

    public static void sword(BaseModel baseModel, GameMap gameMap) {
        Cell cell = gameMap.getCell(baseModel.getPosition());
        cell.setItem(new Sword(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
        gameMap.addToGameObjectList(cell);
    }

    public static void armor(BaseModel baseModel, GameMap gameMap) {
        Cell cell = gameMap.getCell(baseModel.getPosition());
        cell.setItem(new Armor(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
        gameMap.addToGameObjectList(cell);
    }

    public static void key(BaseModel baseModel, GameMap gameMap) {
        Cell cell = gameMap.getCell(baseModel.getPosition());
        cell.setItem(new Key(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
        gameMap.addToGameObjectList(cell);
    }

    public static void torch(BaseModel baseModel, GameMap gameMap) {
        Cell cell = gameMap.getCell(baseModel.getPosition());
        cell.setItem(new Torch(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
        gameMap.addToGameObjectList(cell);
    }

}
