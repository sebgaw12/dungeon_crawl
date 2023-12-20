package com.codecool.dungeoncrawl.fxmlController.util;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import lombok.Getter;

public enum StatisticsTileId {
    HEALTH(TileId.of(26, 22)),
    ATTACK(TileId.of(2, 28)),
    DEFENSE(TileId.of(5, 26)),
    EXPERIENCE(TileId.of(23, 4));

    @Getter
    private final TileId tileId;

    StatisticsTileId(TileId tileId) {
        this.tileId = tileId;
    }


}
