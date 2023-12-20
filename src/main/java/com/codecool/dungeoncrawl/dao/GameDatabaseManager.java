package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.model.*;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public class GameDatabaseManager {
    @Getter
    private PlayerDaoJdbc playerDao;
    private GameStateDaoJdbc gameStateDao;
    private MonsterDaoJdbc monsterDao;
    private ItemDaoJdbc itemDao;
    private InteractiveObjectDaoJdbc interactiveObjectDao;
    private final GameState gameState = GameState.getInstance();

    Dotenv dotenv = Dotenv.load();
    private final String DB_NAME = dotenv.get("DB_NAME");
    private final String DB_USER = dotenv.get("DB_USER");
    private final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    @SneakyThrows
    public void setup() {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        monsterDao = new MonsterDaoJdbc(dataSource);
        itemDao = new ItemDaoJdbc(dataSource);
        interactiveObjectDao = new InteractiveObjectDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        model.setId(playerDao.getRecentPlayerId());
        gameState.setPlayer(model);
        playerDao.add(model);
    }

    public void saveGameState(String mapFileName) {
        gameState.setCurrentMap(mapFileName);
        gameState.setSavedAt(new Timestamp(System.currentTimeMillis()));
        gameStateDao.add(gameState);
    }

    public void saveMonsters(List<ActorEnemy> monsters) {
        for (Actor monsterActor : monsters) {
            MonsterModel model = new MonsterModel(monsterActor);
            monsterDao.add(model, playerDao.getRecentPlayerId());
        }
    }

    public void saveObjects(List<InteractiveObject> objects) {
        for (InteractiveObject object : objects) {
            InteractiveObjectModel model = new InteractiveObjectModel(object);
            interactiveObjectDao.add(model, playerDao.getRecentPlayerId());
        }
    }

    public void saveItems(List<Item> items) {
        for (Item item : items) {
            ItemModel model = new ItemModel(item);
            itemDao.add(model, playerDao.getRecentPlayerId());
        }
    }


    public GameState load(int playerId) {

        PlayerModel player = playerDao.get(playerId);
        List<MonsterModel> monsterModels = monsterDao.get(playerId);
        List<ItemModel> itemModels = itemDao.get(playerId);
        List<InteractiveObjectModel> objects = interactiveObjectDao.get(playerId);

        GameState gameState = gameStateDao.get(playerId);
        System.out.println("game state loaded");

        gameState.setPlayer(player);
        System.out.println("player loaded");

        gameState.setMonsters(monsterModels);
        System.out.println("monsters loaded");

        gameState.setItems(itemModels);
        System.out.println("items loaded");

        gameState.setInteractiveObjects(objects);
        System.out.println("objects loaded");

        return gameState;
    }

    @SneakyThrows
    private DataSource connect() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(DB_NAME);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
