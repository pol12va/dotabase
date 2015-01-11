package org.qulix.dotabase.dao.player.impl;

import org.qulix.dotabase.entity.Player;
import org.qulix.dotabase.enums.Role;
import org.qulix.dotabase.dao.player.PlayerDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class JdbcPlayerDAO implements PlayerDAO {
    private static final String SELECT_PLAYER_BY_ID = "select * from Player where id = ?";
    private static final String SELECT_PLAYER_BY_TEAM_ID = "select * from Player where team_id = ?";
    private static final String ADD_PLAYER = "insert into " +
            "Player (nickname, first_name, last_name, role, date_of_birth, country, team_id) " +
            "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PLAYER = "update Player " +
            "set nickname=?, first_name=?, last_name=?, role=?, date_of_birth=?, country=?, team_id=? " +
            "where id=?";
    private static final String DELETE_PLAYER = "delete from Player where id=?";

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
    public List<Player> getPlayersByTeam(long teamId) {
        List<Player> players = this.jdbcTemplate.query(SELECT_PLAYER_BY_TEAM_ID,
                new Object[]{teamId},
                new PlayerMapper());

        return players;
    }

    @Override
    public void addPlayer(Player player) {
        this.jdbcTemplate.update(ADD_PLAYER,
                player.getNickname(), player.getFirstName(), player.getLastName(),
                player.getRole().toString(), player.getDateOfBirth().getTime(), player.getCountry(), player.getTeamId());
    }

    @Override
    public void editPlayer(long id, Player player) {
        this.jdbcTemplate.update(UPDATE_PLAYER,
                player.getNickname(), player.getFirstName(), player.getLastName(), player.getRole().toString(),
                player.getDateOfBirth().getTime(), player.getCountry(), player.getTeamId(), id);
    }

    @Override
    public void removePlayer(long id) {
        jdbcTemplate.update(DELETE_PLAYER, id);
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
            player.setDateOfBirth(new Date(resultSet.getLong("date_of_birth")));
            player.setCountry(resultSet.getString("country"));
            player.setTeamId(resultSet.getLong("team_id"));

            return player;
        }
    }
}
