package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

public interface GameStateDao {
    void add(GameState state);

    GameState get(int id);
}