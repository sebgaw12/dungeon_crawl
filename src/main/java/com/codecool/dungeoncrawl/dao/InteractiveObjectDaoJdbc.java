package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InteractiveObjectModel;
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
public class InteractiveObjectDaoJdbc implements InteractiveObjectDao {
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void add(InteractiveObjectModel interactiveObjectModel, int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "insert into interactive_object (position_x, position_y, " +
                    "is_interacted, player_id, object_type) values" +
                    "(?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, interactiveObjectModel.getPositionX());
            statement.setInt(2, interactiveObjectModel.getPositionY());
            statement.setBoolean(3, interactiveObjectModel.isInteracted());
            statement.setInt(4, player_id);
            statement.setString(5, interactiveObjectModel.getName());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public List<InteractiveObjectModel> get(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select * from interactive_object where player_id =?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet rs = statement.executeQuery();
            List<InteractiveObjectModel> objects = new ArrayList<>();
            while (rs.next()) {
                objects.add(new InteractiveObjectModel());
                objects.get(objects.size() - 1).setPositionX(rs.getInt("position_x"));
                objects.get(objects.size() - 1).setPositionY(rs.getInt("position_y"));
                objects.get(objects.size() - 1).setInteracted(rs.getBoolean("is_interacted"));
                objects.get(objects.size() - 1).setName(rs.getString("object_type"));
            }
            return objects;
        }
    }
}
