package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import lombok.Getter;
import lombok.Setter;

public class PlayerModel implements BaseModel {
    @Getter
    @Setter
    private String playerName;
    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private int positionX;
    @Getter
    @Setter
    private int positionY;
    @Getter
    @Setter
    private int id;

    public PlayerModel(String playerName, int health, int positionX, int positionY) {
        this.playerName = playerName;
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.positionX = player.getPosition().x();
        this.positionY = player.getPosition().y();
        this.health = player.getHealth();
    }

    @Override
    public Position getPosition() {
        return Position.of(positionX, positionY);
    }
}