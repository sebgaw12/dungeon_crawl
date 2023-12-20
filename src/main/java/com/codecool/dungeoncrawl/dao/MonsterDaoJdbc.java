package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonsterModel;
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
public class MonsterDaoJdbc implements MonsterDao {
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void add(MonsterModel monster, int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "insert into monster (health, defense, attack, " +
                    "field_of_view, position_x, position_y, player_id, monster_type) " +
                    "values(? ,? ,? ,? ,? ,?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, monster.getHealth());
            statement.setInt(2, monster.getDefense());
            statement.setInt(3, monster.getAttack());
            statement.setInt(4, monster.getFieldOfView());
            statement.setInt(5, monster.getPositionX());
            statement.setInt(6, monster.getPositionY());
            statement.setInt(7, playerId);
            statement.setString(8, monster.getName());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public List<MonsterModel> get(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select * from monster where player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet rs = statement.executeQuery();
            List<MonsterModel> monsters = new ArrayList<>();
            while (rs.next()) {
                monsters.add(new MonsterModel());
                monsters.get(monsters.size() - 1).setHealth(rs.getInt("health"));
                monsters.get(monsters.size() - 1).setDefense(rs.getInt("defense"));
                monsters.get(monsters.size() - 1).setAttack(rs.getInt("attack"));
                monsters.get(monsters.size() - 1).setFieldOfView(rs.getInt("field_of_view"));
                monsters.get(monsters.size() - 1).setPositionX(rs.getInt("position_x"));
                monsters.get(monsters.size() - 1).setPositionY(rs.getInt("position_y"));
                monsters.get(monsters.size() - 1).setName(rs.getString("monster_type"));
            }
            return monsters;
        }
    }
}
