package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.engine.utils.Movement;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import lombok.Getter;
import lombok.Setter;


public abstract class ActorEnemy extends Actor {
    @Getter
    @Setter
    private int experienceYield = 2;

    public ActorEnemy(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(GameMap map);

    public boolean isPlayerAttackable(GameMap gameMap, Position position) {
        int RADIUS = 1;
        int min_x = Integer.max(0, position.x() - RADIUS);
        int max_x = Integer.min(gameMap.getCells()[0].length - 1, position.x() + RADIUS);
        int min_y = Integer.max(0, position.y() - RADIUS);
        int max_y = Integer.min(gameMap.getCells().length - 1, position.y() + RADIUS);

        for (int i = min_x; i <= max_x; i++) {
            for (int j = min_y; j <= max_y; j++) {
                if (gameMap.getCells()[i][j].getActor() instanceof Player) {
                    return true;
                }
            }
        }
        return false;
    }

    public void attackPlayer() {
        Player player = Player.getInstance();
        player.setHealth(player.getHealth() - Math.max(getAttack() - player.getDefense(), 0));

        GameMessage.getInstance().addToLogStash(GameMessageSnippet.MONSTER_DAMAGE_DONE.getMessage() + getAttack());
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.PLAYER_DAMAGE_TAKEN.getMessage() + Math.max(getAttack() - player.getDefense(), 0));

    }
}
