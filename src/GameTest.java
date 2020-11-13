import java.util.List;
import entities.Game;
import service.GameService;

public class GameTest {
	 
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Game game1 = new Game("3", 31, "Mariano");
        Game game2 = new Game("2", 78, "Juanito");
        System.out.println("*** Persist - start ***");
        gameService.persist(game1);
        gameService.persist(game2);
        List<Game> games1 = gameService.findAll();
        System.out.println("Games Persisted are :");
        for (Game b : games1) {
            System.out.println("-" + b.toString());
        }
        System.out.println("*** Persist - end ***");
         System.exit(0);
    }
}