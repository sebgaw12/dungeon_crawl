package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class ActorConsumer {

    private ActorConsumer() {

    }

    public static void skeleton(Cell cell) {
        cell.setActor(new Skeleton(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void ogre(Cell cell) {
        cell.setActor(new Ogre(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void mage(Cell cell) {
        cell.setActor(new Mage(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void paladin(Cell cell) {
        cell.setActor(new Paladin(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void warrior(Cell cell) {
        cell.setActor(new Warrior(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void archer(Cell cell) {
        cell.setActor(new Archer(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void archMage(Cell cell) {
        cell.setActor(new ArchMage(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void player(Cell cell) {
        Player.getInstance().setPosition(cell.getPosition());
        cell.setActor(Player.getInstance());
    }
}
