package org.qulix.dotabase;

import org.qulix.dotabase.entity.Player;
import org.qulix.dotabase.dao.player.PlayerDAO;
import org.qulix.dotabase.enums.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDao");
        printSquad(playerDAO, 1);
    }

    private static void insertPlayerTest(PlayerDAO playerDAO) {
        playerDAO.addPlayer(createNewPlayer());
        printSquad(playerDAO, 1);
    }

    private static void updatePlayerTest(PlayerDAO playerDAO) {
        playerDAO.editPlayer(4, createNewPlayer());
        printSquad(playerDAO, 1);
    }

    private static void removePlayerTest(PlayerDAO playerDAO) {
        playerDAO.removePlayer(5);
        printSquad(playerDAO, 1);
    }

    private static void printSquad(PlayerDAO playerDAO, long teamId) {
        List<Player> players = playerDAO.getPlayersByTeam(teamId);
        for (Player player : players) {
            System.out.println(player.getId() + ". " + player.getFirstName() + " " + player.getLastName() + "(" +player.getCountry() + ")");
        }
    }

    private static Player createNewPlayer() {
        Player player = new Player();
        player.setNickname("Funn1k");
        player.setFirstName("Gleb");
        player.setLastName("Lipatnikov");
        player.setRole(Role.OFFLANE);
        player.setCountry("Ukraine");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1992, 10, 16);
        Date date = calendar.getTime();

        player.setDateOfBirth(date);
        player.setTeamId(1);

        return player;
    }
}
