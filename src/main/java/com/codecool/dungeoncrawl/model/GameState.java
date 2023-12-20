package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

public class GameState implements BaseModel {
    @Getter
    @Setter
    private Timestamp savedAt;
    @Setter
    @Getter
    private String currentMap;
    @Setter
    @Getter
    private PlayerModel player;
    @Setter
    @Getter
    private List<MonsterModel> monsters;
    @Setter
    @Getter
    private List<ItemModel> items;
    @Setter
    @Getter
    private List<InteractiveObjectModel> interactiveObjects;
    private static GameState singleInstance = null;

    public GameState() {
        this.currentMap = null;
        this.savedAt = null;
        this.player = null;
    }

    public GameState(String currentMap) {
        this.currentMap = currentMap;
    }

    public static GameState getInstance() {
        if (singleInstance == null) {
            singleInstance = new GameState();
        }
        return singleInstance;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}