package org.qulix.dotabase.dao.player;

import org.qulix.dotabase.entity.Player;

import java.util.List;

public interface PlayerDAO {
    Player getPlayer(long id);
    List<Player> getPlayersByTeam(long teamId);
    void addPlayer(Player player);
    void editPlayer(long id, Player player);
    void removePlayer(long id);
}
