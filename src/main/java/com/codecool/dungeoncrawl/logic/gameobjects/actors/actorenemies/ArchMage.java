package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Movement;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.enemylogic.Behavior;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class ArchMage extends ActorEnemy {

    public ArchMage(Position position) {
        super(ActorTileId.ARCHMAGE.getTileId(), position);
        this.setHealth(10);
        this.setAttack(4);
        this.setDefense(2);
        this.setFieldOfViewDistance(8);
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
