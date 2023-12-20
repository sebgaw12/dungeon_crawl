package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Movement;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.enemylogic.Behavior;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class Skeleton extends ActorEnemy {
    public Skeleton(Position position) {
        super(ActorTileId.SKELETON.getTileId(), position);
        this.setHealth(6);
    }

    @Override
    public Movement planMovement(GameMap map) {
        Behavior behavior = new Behavior();
        Position newPosition = behavior.guard(map, this);
        if (isPlayerAttackable(map, getPosition())) {
            attackPlayer();
            return Movement.of(getPosition(), getPosition());
        }
        return Movement.of(getPosition(), newPosition);
    }

}
