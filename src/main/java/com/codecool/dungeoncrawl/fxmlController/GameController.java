package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.fileloader.MapLoader;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.awt.*;

public class GameController {
    @Getter
    @Setter
    private GameMap gameMap = MapLoader.loadMap("/tutorial.txt");
    @Setter
    @Getter
    private GameWindow gameWindow;
    @Setter
    @Getter
    private GUIWindow guiWindow;
    @FXML
    private Pane deathPane;

    @FXML
    protected void handleKeyEvent(KeyEvent keyEvent) {
        handlePlayerDeath();
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.S) {
            saveGame();
            GameMessage.getInstance().addToLogStash(GameMessageSnippet.GAME_SAVED.getMessage());
            guiWindow.refreshInterface();
            return;
        }
        handleNonBlockingEvents(keyEvent);
        handleBlockingEvents(keyEvent);
    }

    private void handleNonBlockingEvents(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP, W -> gameMap.handleMoveActorPlayer(Direction.UP);
            case DOWN, S -> gameMap.handleMoveActorPlayer(Direction.DOWN);
            case LEFT, A -> gameMap.handleMoveActorPlayer(Direction.LEFT);
            case RIGHT, D -> gameMap.handleMoveActorPlayer(Direction.RIGHT);
            case G -> pickUpItem(); // Grabs item from floor
            case F -> interactWithEnvironment(); // Interact with game surroundings
            case E -> gameMap = gameMap.getAnotherMap();
            case ESCAPE -> System.exit(0);
            case DIGIT1 -> useItem(0);
            case DIGIT2 -> useItem(1);
            case DIGIT3 -> useItem(2);
            case DIGIT4 -> useItem(3);
            case DIGIT5 -> useItem(4);
            case DIGIT6 -> useItem(5);
            case DIGIT7 -> useItem(6);
            case DIGIT8 -> useItem(7);
            case DIGIT9 -> useItem(8);
            default -> {
                return;
            }
        }
        gameMap.handleMoveActorEnemy();
        gameWindow.refresh(gameMap);
        guiWindow.refreshInterface();
    }

    private void handleBlockingEvents(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I -> guiWindow.showInventory();
            case Q -> guiWindow.showMessageLogStash();
            case C -> guiWindow.showStatistics();
        }
    }

    private void pickUpItem() {
        gameMap.getPlayer().pickUpItem(gameMap);
    }

    private void interactWithEnvironment() {
        gameMap.getPlayer().interactWithObject(gameMap);
    }

    private void useItem(int inventorySlot) {
        gameMap.getPlayer().useItem(inventorySlot);
    }

    private void saveGame() {
        gameMap.save();
    }

    @SneakyThrows
    private void handlePlayerDeath(){
        // UNCOMMENT TO MAKE PLAYER ABLE TO DIE
//        if (gameMap.getPlayer().isDead()) {
//            deathPane.setVisible(true);
//            deathPane.getChildren().add(new Text("You Died!"));
//            Thread.sleep(5000);
//            System.exit(0);
//        }
    }
}
