package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.FieldOfView;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Ogre;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;

public class Behavior {
    public Position goToPatrolPlace(GameMap map, Ogre ogre) {
        EnemyMovement enemyMovement = new EnemyMovement();
        FieldOfView fieldOfView = new FieldOfView();
        Position ogrePosition = ogre.getPosition();
        Position patrolDestination = ogre.getPatrolDestination();
        int vector = ogrePosition.x() - patrolDestination.x();

        if (ogrePosition.x() - patrolDestination.x() == 0) {
            ogre.switchPatrol();
        }
        Position moveVector;
        if (fieldOfView.isPlayerNear(map, ogre)) {
            moveVector = vectorToPlayer(map, ogre);
            return enemyMovement.moveTowardsPlayer(map, ogre, moveVector);
        }
        if (vector > 0) {
            moveVector = Direction.LEFT.getPosition();
        } else {
            moveVector = Direction.RIGHT.getPosition();
        }
        return enemyMovement.handlePatrol(map, ogre, moveVector);
    }

    public boolean isPlayerThere(GameMap map, Position vector, Position enemyPosition) {
        Position playerPosition = map.getPlayer().getPosition();
        return enemyPosition.x() + vector.x() == playerPosition.x() &&
                enemyPosition.y() + vector.y() == playerPosition.y();
    }

    public Position guard(GameMap map, ActorEnemy actorEnemy) {
        FieldOfView fieldOfView = new FieldOfView();
        Position actorPosition = actorEnemy.getPosition();
        EnemyMovement enemyMovement = new EnemyMovement();
        Position vector = vectorToPlayer(map, actorEnemy);

        int dx = Integer.compare(vector.x(), 0);
        int dy = Integer.compare(vector.y(), 0);
        Position moveVector = Position.of(dx, dy);
        if (isPlayerThere(map, moveVector, actorPosition)) {
            return actorPosition;
        } else if (fieldOfView.isPlayerNear(map, actorEnemy) && enemyMovement.isMovePossible(map, moveVector, actorPosition)) {
            return enemyMovement.moveTowardsPlayer(map, actorEnemy, moveVector);
        }
        return actorPosition;
    }

    public Position vectorToPlayer(GameMap map, ActorEnemy actor) {
        Position playerPosition = map.getPlayer().getPosition();
        Position position = actor.getPosition();

        if (Math.abs(playerPosition.x() - position.x()) >= Math.abs(playerPosition.y() - position.y())) {
            if (playerPosition.x() - position.x() > 0) {
                return Direction.RIGHT.getPosition();
            }
            return Direction.LEFT.getPosition();
        }
        if (playerPosition.y() - position.y() > 0) {
            return Direction.DOWN.getPosition();
        }
        return Direction.UP.getPosition();
    }
}
