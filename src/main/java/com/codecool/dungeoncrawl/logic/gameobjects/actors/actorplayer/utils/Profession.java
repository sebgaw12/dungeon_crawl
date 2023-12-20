package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;

import java.util.function.Consumer;

public enum Profession {
    WARRIOR(ProfessionTileId.WARRIOR, ProfessionConsumer::warrior),
    MAGE(ProfessionTileId.MAGE, ProfessionConsumer::mage),
    HUNTER(ProfessionTileId.HUNTER, ProfessionConsumer::hunter);

    private final ProfessionTileId professionTileId;
    private final Consumer<Player> strategy;

    Profession(ProfessionTileId professionTileId, Consumer<Player> strategy) {
        this.professionTileId = professionTileId;
        this.strategy = strategy;
    }

    public void apply(Player player) {
        strategy.accept(player);
    }

    public static Profession assignProfession(ProfessionTileId professionTileId) {
        for (Profession profession : values()) {
            if (profession.professionTileId == professionTileId) {
                return profession;
            }
        }
        return HUNTER; // Basic profession if none was chosen by user
    }
}
