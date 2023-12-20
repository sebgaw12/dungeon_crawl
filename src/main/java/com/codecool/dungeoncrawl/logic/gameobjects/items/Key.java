package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;
import lombok.Getter;

public class Key implements Item {
    private final TileId tileId = ItemTileId.KEY.getTileId();
    @Getter
    private Position position;

    public Key(Position position) {
        this.position = position;
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {

    }

}
