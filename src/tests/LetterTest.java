package tests;

import java.util.List;
import entities.Letter;
import service.LetterService;

public class LetterTest {
	 
    public static void main(String[] args) {
        LetterService letterService = new LetterService();
        Letter letter1 = new Letter("a");
        Letter letter2 = new Letter("b");
        Letter letter3 = new Letter("c");
        System.out.println("*** Persist - start ***");
        letterService.persist(letter1);
        letterService.persist(letter2);
        letterService.persist(letter3);
        List<Letter> letters1 = letterService.findAll();
        System.out.println("Letters Persisted are :");
        for (Letter l : letters1) {
            System.out.println("-" + l.toString());
        }
        System.out.println("*** Persist - end ***");
         System.exit(0);
    }
}