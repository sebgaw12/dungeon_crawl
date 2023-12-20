package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import lombok.Getter;

public enum ProfessionTileId {
    WARRIOR(TileId.of(31, 0)),
    MAGE(TileId.of(24, 1)),
    HUNTER(TileId.of(30, 1));

    @Getter
    private final TileId tileId;

    ProfessionTileId(TileId tileId) {
        this.tileId = tileId;
    }

}
