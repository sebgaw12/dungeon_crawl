package com.codecool.dungeoncrawl.dao;


import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.List;

public interface ItemDao {
    void add(ItemModel itemModel, int player_id);

    List<ItemModel> get(int player_id);
}
