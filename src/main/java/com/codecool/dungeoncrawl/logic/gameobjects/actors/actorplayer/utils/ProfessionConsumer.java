package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;

public class ProfessionConsumer {
    private ProfessionConsumer() {

    }

    public static void warrior(Player player) {
        player.setTileId(ProfessionTileId.WARRIOR.getTileId());
        player.setMaxHealth(25);
        player.setHealth(25);
        player.setDefense(3);
        player.setAttack(1);
    }

    public static void mage(Player player) {
        player.setTileId(ProfessionTileId.MAGE.getTileId());
        player.setMaxHealth(15);
        player.setHealth(15);
        player.setDefense(1);
        player.setAttack(3);
    }

    public static void hunter(Player player) {
        player.setTileId(ProfessionTileId.HUNTER.getTileId());
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setDefense(2);
        player.setAttack(2);
    }
}
