package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;
import lombok.Getter;

public class Food implements Item {
    public final int value = 2;
    private final TileId tileId = ItemTileId.FOOD.getTileId();
    @Getter
    private Position position;

    public Food() {

    }

    public Food(Position position) {
        this.position = position;
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {
        if (player.getHealth() <= player.getMaxHealth() - value) {
            player.removeFromInventory(this);
            player.setHealth(player.getHealth() + value);
            addMessageToLog();
        }
    }
}
