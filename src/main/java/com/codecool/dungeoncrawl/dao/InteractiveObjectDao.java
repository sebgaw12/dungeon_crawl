package com.codecool.dungeoncrawl.dao;


import com.codecool.dungeoncrawl.model.InteractiveObjectModel;

import java.util.List;

public interface InteractiveObjectDao {
    void add(InteractiveObjectModel interactiveObjectModel, int playerId);
    List<InteractiveObjectModel> get(int playerId);
}
