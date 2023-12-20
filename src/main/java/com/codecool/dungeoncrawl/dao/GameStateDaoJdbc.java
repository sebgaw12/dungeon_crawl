package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.*;

@Data
@AllArgsConstructor
public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) values (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, new Timestamp(state.getSavedAt().getTime()));
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public GameState get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new GameState(resultSet.getString("current_map"));
            }
        }
        return null;
    }

}
