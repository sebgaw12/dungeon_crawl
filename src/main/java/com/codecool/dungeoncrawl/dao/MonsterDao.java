package com.codecool.dungeoncrawl.dao;


import com.codecool.dungeoncrawl.model.MonsterModel;

import java.util.List;

public interface MonsterDao {

    void add(MonsterModel monster, int playerId);

    List<MonsterModel> get(int id);
}
