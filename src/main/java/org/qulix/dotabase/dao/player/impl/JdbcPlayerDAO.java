package org.qulix.dotabase.dao.player.impl;

import org.qulix.dotabase.entity.Player;
import org.qulix.dotabase.enums.Role;
import org.qulix.dotabase.dao.player.PlayerDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcPlayerDAO implements PlayerDAO {
    private static final String SELECT_PLAYER_BY_ID = "select * from Player where id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Player getPlayer(long id) {
        Player player = this.jdbcTemplate.queryForObject(SELECT_PLAYER_BY_ID,
                new Object[] {id},
                new PlayerMapper());

        return player;
    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void editPlayer(long id, Player player) {

    }

    @Override
    public void removePlayer(long id) {

    }

    private static final class PlayerMapper implements RowMapper<Player> {
        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {
            Player player = new Player();
            player.setId(resultSet.getLong("id"));
            player.setNickname(resultSet.getString("nickname"));
            player.setFirstName(resultSet.getString("first_name"));
            player.setLastName(resultSet.getString("last_name"));
            player.setRole(Role.fromString(resultSet.getString("role")));
            player.setAge(resultSet.getInt("age"));
            player.setCountry(resultSet.getString("country"));
            player.setTeamId(resultSet.getLong("team_id"));

            return player;
        }
    }
}
