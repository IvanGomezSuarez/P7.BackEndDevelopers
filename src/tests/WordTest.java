package tests;

import java.util.List;
import entities.Word;
import service.WordService;

public class WordTest {
	 
    public static void main(String[] args) {
    	devuelvePalabras();
        /*
        WordService wordService = new WordService();
        Word word1 = new Word("Develoteca");
        Word word2 = new Word("Plugins");
        Word word3 = new Word("Tutoriales");
        System.out.println("*** Persist - start ***");
        wordService.persist(word1);
        wordService.persist(word2);
        wordService.persist(word3);
        List<Word> words1 = wordService.findAll();
        System.out.println("Words Persisted are :");
        for (Word l : words1) {
            System.out.println("-" + l.toString());
        }
        System.out.println("*** Persist - end ***");
         System.exit(0);
         */
    }
    
    public static List<Word> devuelvePalabras() {
    	WordService wordservice = new WordService();
    	List<Word> palabras = wordservice.findAll();
    	System.out.println(palabras.toString());
    	return palabras;
    	}
}