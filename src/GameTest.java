import java.util.List;
import entities.Game;
import service.GameService;

public class GameTest {
	 
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Game game1 = new Game("1", "Mariano", 31);
        Game game2 = new Game("2", "Juanito", 78);
        System.out.println("*** Persist - start ***");
        gameService.persist(game1);
        gameService.persist(game2);
        List<Game> games1 = gameService.findAll();
        System.out.println("Books Persisted are :");
        for (Game b : games1) {
            System.out.println("-" + b.toString());
        }
        System.out.println("*** Persist - end ***");
         System.exit(0);
    }
}