package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;
import lombok.Getter;

public class Torch implements Item {

    public final int value = 1;
    private final TileId tileId = ItemTileId.TORCH.getTileId();
    @Getter
    private Position position;

    public Torch() {
    }

    public Torch(Position position) {
        this.position = position;
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {
        player.setFieldOfViewDistance(player.getFieldOfViewDistance() + value);
        player.removeFromInventory(this);
        addMessageToLog();
    }

}
