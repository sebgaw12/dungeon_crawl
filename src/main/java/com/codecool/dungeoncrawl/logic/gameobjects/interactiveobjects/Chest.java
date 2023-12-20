package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.Util;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;

import java.util.Random;

public class Chest extends InteractiveObject {

    public Chest(Position position) {
        super(InteractiveObjectTileId.CLOSED_CHEST.getTileId(), position);
    }

    public void setOpen() {
        setTileId(InteractiveObjectTileId.OPEN_CHEST.getTileId());
    }

    @Override
    public void interact() {
        if (getTileId() == InteractiveObjectTileId.OPEN_CHEST.getTileId()) {
            return;
        }
        setTileId(InteractiveObjectTileId.OPEN_CHEST.getTileId());
        Player.getInstance().addToInventory((generateRandomItem()));
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.OPEN_UP_INTERACTIVE_OBJECT.getMessage() + this.getClass().getSimpleName());
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    private Item generateRandomItem() {
        Random random = new Random();
        int index = random.nextInt(Util.classNames.length);

        switch (Util.classNames[index]) {
            case "ARMOR" -> {
                return new Armor();
            }
            case "SWORD" -> {
                return new Sword();
            }
            case "FOOD" -> {
                return new Food();
            }
            case "TORCH" -> {
                return new Torch();
            }
            default -> throw new IllegalArgumentException("Invalid class name: " + Util.classNames[index]);
        }
    }
}
