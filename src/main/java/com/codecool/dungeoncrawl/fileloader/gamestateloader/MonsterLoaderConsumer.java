package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils.Profession;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

public class MonsterLoaderConsumer {
    private MonsterLoaderConsumer() {
    }

    public static void skeleton(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        Skeleton skeleton = new Skeleton(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        skeleton.setHealth(monsterModel.getHealth());
        skeleton.setDefense(monsterModel.getDefense());
        skeleton.setAttack(monsterModel.getAttack());
        cell.setActor(skeleton);
        gameMap.addToGameObjectList(cell);
    }

    public static void mage(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        Mage mage = new Mage(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        mage.setHealth(monsterModel.getHealth());
        mage.setDefense(monsterModel.getDefense());
        mage.setAttack(monsterModel.getAttack());
        cell.setActor(mage);
        gameMap.addToGameObjectList(cell);
    }

    public static void ogre(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        Ogre ogre = new Ogre(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        ogre.setHealth(monsterModel.getHealth());
        ogre.setDefense(monsterModel.getDefense());
        ogre.setAttack(monsterModel.getAttack());
        cell.setActor(ogre);
        gameMap.addToGameObjectList(cell);
    }

    public static void paladin(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        Paladin paladin = new Paladin(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        paladin.setHealth(monsterModel.getHealth());
        paladin.setDefense(monsterModel.getDefense());
        paladin.setAttack(monsterModel.getAttack());
        cell.setActor(paladin);
        gameMap.addToGameObjectList(cell);
    }

    public static void warrior(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        Warrior warrior = new Warrior(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        warrior.setHealth(monsterModel.getHealth());
        warrior.setDefense(monsterModel.getDefense());
        warrior.setAttack(monsterModel.getAttack());
        cell.setActor(warrior);
        gameMap.addToGameObjectList(cell);
    }

    public static void archer(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        Archer archer = new Archer(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        archer.setHealth(monsterModel.getHealth());
        archer.setDefense(monsterModel.getDefense());
        archer.setAttack(monsterModel.getAttack());
        cell.setActor(archer);
        gameMap.addToGameObjectList(cell);
    }

    public static void archMage(BaseModel baseModel, GameMap gameMap) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Cell cell = gameMap.getCell(monsterModel.getPosition());
        ArchMage archMage = new ArchMage(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        archMage.setHealth(monsterModel.getHealth());
        archMage.setDefense(monsterModel.getDefense());
        archMage.setAttack(monsterModel.getAttack());
        cell.setActor(archMage);
        gameMap.addToGameObjectList(cell);
    }
    public static void player(BaseModel baseModel, GameMap gameMap) {
        PlayerModel playerModel = (PlayerModel) baseModel;
        Cell cell = gameMap.getCell(playerModel.getPosition());
        Player player = Player.getInstance();
        player.setPosition(playerModel.getPosition());
        player.setHealth(playerModel.getHealth());
        player.setName(playerModel.getPlayerName());
        Profession.MAGE.apply(player);
        cell.setActor(player);
        gameMap.setPlayer(player);
    }

}
