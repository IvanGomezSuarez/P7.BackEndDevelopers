package controlador;
import java.util.List;

import org.hibernate.Session;

import entities.Game;
import service.GameService;

public class GameTest {
	 
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        Game game = new Game(); 
        game.setUser("Manolo");
        game.setScore(25);
        session.save(game);
        session.getTransaction().commit();
    }
}