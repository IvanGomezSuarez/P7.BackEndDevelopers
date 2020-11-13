package service;

import java.util.List;

import dao.WordDao;
import entities.Word;

public class WordService {
	 
    private static WordDao wordDao;
 
    public WordService() {
        wordDao = new WordDao();
    }
 
    public void persist(Word entity) {
    	wordDao.openCurrentSessionwithTransaction();
    	wordDao.persist(entity);
    	wordDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Word entity) {
    	wordDao.openCurrentSessionwithTransaction();
    	wordDao.update(entity);
    	wordDao.closeCurrentSessionwithTransaction();
    }
 
    public Word findById(String id) {
    	wordDao.openCurrentSession();
        Word word = wordDao.findById(id);
        wordDao.closeCurrentSession();
        return word;
    }
 
    public void delete(String id) {
    	wordDao.openCurrentSessionwithTransaction();
        Word word = wordDao.findById(id);
        wordDao.delete(word);
        wordDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Word> findAll() {
    	wordDao.openCurrentSession();
        List<Word> word = wordDao.findAll();
        wordDao.closeCurrentSession();
        return word;
    }
 
    public void deleteAll() {
    	wordDao.openCurrentSessionwithTransaction();
    	wordDao.deleteAll();
    	wordDao.closeCurrentSessionwithTransaction();
    }
 
    public WordDao wordDao() {
        return wordDao;
    }
}