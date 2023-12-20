package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.Renderable;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;

public interface Item extends Renderable {
    @Override
    TileId getTileId();

    Position getPosition();

    void onUse(Player player);

    default void addMessageToLog() {
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.USE_ITEM.getMessage() + this.getClass().getSimpleName());
    }

}
