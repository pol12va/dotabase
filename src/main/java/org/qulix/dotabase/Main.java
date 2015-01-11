package org.qulix.dotabase;

import org.qulix.dotabase.entity.Player;
import org.qulix.dotabase.dao.player.PlayerDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDao");

        Player player = playerDAO.getPlayer(2);
        System.out.println(player.getFirstName() + " " + player.getLastName());
    }
}
