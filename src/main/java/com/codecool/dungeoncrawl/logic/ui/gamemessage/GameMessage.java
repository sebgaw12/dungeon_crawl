package com.codecool.dungeoncrawl.logic.ui.gamemessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameMessage {

    private final List<String> logStash = new ArrayList<>();

    private static GameMessage single_instance = null;

    public GameMessage() {

    }

    public static GameMessage getInstance() {
        if (Objects.isNull(single_instance)) {
            single_instance = new GameMessage();
        }
        return single_instance;
    }

    public void addToLogStash(String gameMessage) {
        logStash.add(gameMessage);
    }

    public List<String> getLogStash() {
        return new ArrayList<>(logStash);
    }

}
