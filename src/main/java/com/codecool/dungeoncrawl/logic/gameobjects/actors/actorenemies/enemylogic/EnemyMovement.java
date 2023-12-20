package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.FieldOfView;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Ogre;

public class EnemyMovement {
    public boolean isMovePossible(GameMap map, Position vector, Position actorPosition) {
        Cell cell = map.getCell(Position.of(actorPosition.x() + vector.x(), actorPosition.y() + vector.y()));
        return cell.isWalkable();
    }

    public Position handlePatrol(GameMap map, Ogre ogre, Position movementVector) {
        EnemyMovement enemyMovement = new EnemyMovement();
        Behavior behavior = new Behavior();
        if (behavior.isPlayerThere(map, movementVector, ogre.getPosition())) {
            return ogre.getPosition();
        }
        if (enemyMovement.isMovePossible(map, movementVector, ogre.getPosition())) {
            return Position.of(ogre.getPosition().x() + movementVector.x(), ogre.getPosition().y() + movementVector.y());
        }
        ogre.switchPatrol();
        return ogre.getPosition();
    }

    public Position moveTowardsPlayer(GameMap map, ActorEnemy actorEnemy, Position moveVector) {
        Behavior behavior = new Behavior();
        FieldOfView fieldOfView = new FieldOfView();

        if (behavior.isPlayerThere(map, moveVector, actorEnemy.getPosition())) {
            return actorEnemy.getPosition();
        }
        if (fieldOfView.isPlayerNear(map, actorEnemy) && isMovePossible(map, moveVector, actorEnemy.getPosition())) {
            return Position.of(actorEnemy.getPosition().x() + moveVector.x(), actorEnemy.getPosition().y() + moveVector.y());
        }
        return actorEnemy.getPosition();
    }

}
