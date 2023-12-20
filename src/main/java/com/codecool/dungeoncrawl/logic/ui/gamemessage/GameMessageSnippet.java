package com.codecool.dungeoncrawl.logic.ui.gamemessage;

import lombok.Getter;

import java.util.function.Supplier;

public enum GameMessageSnippet {
    PICK_ITEM(() -> "You picked up "),
    USE_ITEM(() -> "You used "),
    OPEN_UP_INTERACTIVE_OBJECT(() -> "You open up a "),
    GAME_SAVED(() -> "Game is saved"),
    CLOSE_DOOR(() -> "You close a "),
    CANT_OPEN_DOOR(() -> "It's locked!"),
    TRAVEL_WITH_BOAT(() -> "You have traveled to further location with a "),
    PLAYER_DAMAGE_DONE(() -> "You hit an enemy for "),
    PLAYER_DAMAGE_TAKEN(() -> "Player was hit for "),
    PLAYER_LEVEL_UP(() -> "You leveled up!"),
    PLAYER_HEALTH_UP(() -> "Your max HP is increased!"),
    PLAYER_MOVE_INTO_UNWALKABLE_TILE(() -> "You can't move there!"),
    PLAYER_PICK_NOTHING(() -> "There is nothing to pick up!"),
    PLAYER_INTERACT_WITH_NOTHING(() -> "There is nothing to interact with!"),
    MONSTER_DAMAGE_TAKEN(() -> "Enemy was hit for "),
    MONSTER_DAMAGE_DONE(() -> "Enemy hits player for ");

    private final Supplier<String> message;

    GameMessageSnippet(Supplier<String> message) {
        this.message = message;
    }

    public String getMessage() {
        return message.get();
    }

}
