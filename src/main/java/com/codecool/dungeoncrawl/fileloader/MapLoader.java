package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.fileloader.gamestateloader.GameStateLoader;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils.Profession;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InteractiveObjectModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.MonsterModel;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFileName) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFileName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, TileType.FLOOR);
        map.setMapFileName(mapFileName);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(Position.of(x, y));
                    CharOnMap.fromChar(line.charAt(x)).apply(cell);
                    map.addToGameObjectList(cell);
                }
            }
        }
        return map;
    }

    public static GameMap loadAndPutObjectsOnMap(GameState gameState) {
        GameMap gameMap = MapLoader.loadMap("/empty" + gameState.getCurrentMap().split("/")[1]);

        GameStateLoader.PLAYER.apply(gameState.getPlayer(), gameMap);

        for (MonsterModel monster : gameState.getMonsters()) {
            GameStateLoader.valueOf(monster.getName())
                    .apply(monster, gameMap);
        }
        for (ItemModel item : gameState.getItems()) {
            GameStateLoader.valueOf(item.getName())
                    .apply(item, gameMap);
        }
        for (InteractiveObjectModel interactiveObject : gameState.getInteractiveObjects()) {
            GameStateLoader.valueOf(interactiveObject.getName())
                    .apply(interactiveObject, gameMap);
        }
        return gameMap;
    }

}
