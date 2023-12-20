package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.fileloader.MapLoader;
import com.codecool.dungeoncrawl.logic.engine.utils.Movement;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.ActorPlayer;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Boat;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameMap {
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private Cell[][] cells;
    @Getter
    @Setter
    private Player player;
    @Getter
    @Setter
    private static int mapLevel = 0;
    @Setter
    @Getter
    private String mapFileName;
    private final List<ActorEnemy> monsters = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<InteractiveObject> interactiveObjects = new ArrayList<>();
    private final GameMessage gameMessage = GameMessage.getInstance();
    private final GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();

    public GameMap(int width, int height, TileType defaultTileType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(Position.of(x, y), defaultTileType);
            }
        }
    }

    public void handleMoveActorPlayer(Direction direction) {
        Movement movement = player.planMovement(direction);
        Cell nextCell = cells[movement.newPosition().x()][movement.newPosition().y()];
        Cell currentCell = cells[movement.currentPosition().x()][movement.currentPosition().y()];

        if (Objects.nonNull(nextCell.getActor())) {
            attackEnemy(nextCell);
        }
        if (nextCell.isWalkable()) {
            movePlayer(currentCell, nextCell, movement);
            return;
        }
        gameMessage.addToLogStash(GameMessageSnippet.PLAYER_MOVE_INTO_UNWALKABLE_TILE.getMessage());
    }

    public void handleMoveActorEnemy() {
        for (ActorEnemy monster : monsters) {
            Movement movement = monster.planMovement(this);
            cells[movement.currentPosition().x()][movement.currentPosition().y()].setActor(null);
            cells[movement.newPosition().x()][movement.newPosition().y()].setActor(monster);
            monster.setPosition(movement.newPosition());
        }
    }

    public void addToGameObjectList(Cell cell) {
        if (cell.getActor() instanceof ActorPlayer) {
            this.player = (Player) cell.getActor();
        } else if (Objects.nonNull(cell.getActor())) {
            monsters.add((ActorEnemy) cell.getActor());
        } else if (Objects.nonNull(cell.getItem())) {
            items.add(cell.getItem());
        } else if (Objects.nonNull(cell.getInteractiveObject())) {
            interactiveObjects.add(cell.getInteractiveObject());
        }
    }

    public Cell getCell(Position position) {
        return cells[position.x()][position.y()];
    }

    public GameMap getAnotherMap() {
        if (getCell(player.getPosition()).getInteractiveObject() instanceof Boat) {
            return MapLoader.loadMap("/map" + ++mapLevel + ".txt");
        }
        return this;
    }

    public Cell getPlayerCell() {
        return getCell(player.getPosition());
    }

    public <T extends Item> void removeItemFromGameObjectList(T item) {
        items.remove(item);
    }

    public void save() {
        gameDatabaseManager.setup();
        gameDatabaseManager.savePlayer(player);
        System.out.println("player saved");
        gameDatabaseManager.saveGameState(mapFileName);
        System.out.println("state saved");
        gameDatabaseManager.saveMonsters(monsters);
        System.out.println("monsters saved");
        gameDatabaseManager.saveItems(items);
        System.out.println("items saved");
        gameDatabaseManager.saveObjects(interactiveObjects);
        System.out.println("interactiveObjects saved");
    }

    private void attackEnemy(Cell cell) {
        player.planAttack(cell.getActor());
        if (cell.getActor().isDead()) {
            player.gainExperience(((ActorEnemy) cell.getActor()).getExperienceYield());
            monsters.remove((ActorEnemy) cell.getActor());
            cell.setActor(null);
        }
    }

    private void movePlayer(Cell currentCell, Cell nextCell, Movement movement) {
        currentCell.setActor(null);
        nextCell.setActor(player);
        player.setPosition(movement.newPosition());
    }

}
