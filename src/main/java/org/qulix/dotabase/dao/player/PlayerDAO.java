package org.qulix.dotabase.dao.player;

import org.qulix.dotabase.entity.Player;

public interface PlayerDAO {
    Player getPlayer(long id);
    void addPlayer(Player player);
    void editPlayer(long id, Player player);
    void removePlayer(long id);
}
