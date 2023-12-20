package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ItemDaoJdbc implements ItemDao {

    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void add(ItemModel item, int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "insert into item (position_x, position_y, player_id, item_type) " +
                    "values (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, item.getPositionX());
            statement.setInt(2, item.getPositionY());
            statement.setInt(3, player_id);
            statement.setString(4, item.getName());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public List<ItemModel> get(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select * from item where player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, player_id);
            ResultSet rs = statement.executeQuery();
            List<ItemModel> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ItemModel());
                items.get(items.size() - 1).setPositionX(rs.getInt("position_x"));
                items.get(items.size() - 1).setPositionY(rs.getInt("position_y"));
                items.get(items.size() - 1).setName(rs.getString("item_type"));
            }
            return items;
        }
    }
}
