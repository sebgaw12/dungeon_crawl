package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer;

import com.codecool.dungeoncrawl.logic.engine.utils.Movement;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import lombok.Getter;
import lombok.Setter;

public abstract class ActorPlayer extends Actor {
    @Getter
    @Setter
    private String name;

    public ActorPlayer(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(Direction direction);

}
