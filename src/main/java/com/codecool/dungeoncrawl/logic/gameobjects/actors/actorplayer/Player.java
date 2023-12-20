package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.engine.utils.Movement;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils.Profession;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils.ProfessionTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Player extends ActorPlayer {
    @Getter
    @Setter
    private int maxHealth;
    private final List<Item> inventory = new ArrayList<>();
    @Getter
    private int experience = 0;
    @Getter
    private int maxExperience = 10;
    @Getter
    @Setter
    private String name;
    private static Player single_instance;


    public Player() {
        super(null, null);
        setFieldOfViewDistance(4);
    }

    public static Player getInstance() {
        if (Objects.isNull(single_instance)) {
            single_instance = new Player();
        }
        return single_instance;
    }

    public Movement planMovement(Direction direction) {
        return Movement.of(getPosition(), getNewPosition(direction));
    }

    private Position getNewPosition(Direction direction) {
        return Position.of(getPosition().x() + direction.getPosition().x(), getPosition().y() + direction.getPosition().y());
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    public void interactWithObject(GameMap map) {
        for (Position position : Arrays.stream(Direction.values()).map(Direction::getPosition).toList()) {
            Cell adjacentCell = map.getPlayerCell().getNeighbor(position, map);
            if (Objects.nonNull(adjacentCell.getInteractiveObject())) {
                adjacentCell.getInteractiveObject().interact();
                return;
            }
        }
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.PLAYER_INTERACT_WITH_NOTHING.getMessage());
    }

    public void pickUpItem(GameMap map) {
        if (isInventoryFull() || Objects.isNull(map.getPlayerCell().getItem())) {
            GameMessage.getInstance().addToLogStash(GameMessageSnippet.PLAYER_PICK_NOTHING.getMessage());
            return;
        }
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.PICK_ITEM.getMessage() + map.getPlayerCell().getItem().getClass().getSimpleName());
        addToInventory(map.getPlayerCell().getItem());
        map.removeItemFromGameObjectList(map.getPlayerCell().getItem());
        map.getPlayerCell().setItem(null);
    }

    public void useItem(int itemSlot) {
        if (inventory.size() <= itemSlot) {
            return;
        }
        inventory.get(itemSlot).onUse(this);
    }

    public void setProfession(ProfessionTileId professionTileId) {
        Profession.assignProfession(professionTileId).apply(this);
    }

    public List<Item> getInventory() {
        return new ArrayList<>(inventory);
    }

    private boolean isInventoryFull() {
        return inventory.size() >= 9;
    }

    private int calculateDamage(int enemyDefense) {
        return getAttack() - enemyDefense;
    }

    public <T extends Actor> void planAttack(T enemy) {
        enemy.setHealth(enemy.getHealth() - calculateDamage(enemy.getDefense()));
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.PLAYER_DAMAGE_DONE.getMessage() + getAttack());
        GameMessage.getInstance().addToLogStash(GameMessageSnippet.MONSTER_DAMAGE_TAKEN.getMessage() + calculateDamage(enemy.getDefense()));
    }

    public void gainExperience(int experienceFromEnemies) {
        experience += experienceFromEnemies;
        if (experience >= maxExperience) {
            experience -= maxExperience;
            maxExperience += 10;
            maxHealth += 5;
            GameMessage.getInstance().addToLogStash(GameMessageSnippet.PLAYER_LEVEL_UP.getMessage());
            GameMessage.getInstance().addToLogStash(GameMessageSnippet.PLAYER_HEALTH_UP.getMessage());
        }
    }
}
