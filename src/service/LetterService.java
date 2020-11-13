package service;

import java.util.List;

import dao.LetterDao;
import entities.Letter;

public class LetterService {
	 
    private static LetterDao letterDao;
 
    public LetterService() {
        letterDao = new LetterDao();
    }
 
    public void persist(Letter entity) {
    	letterDao.openCurrentSessionwithTransaction();
    	letterDao.persist(entity);
    	letterDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Letter entity) {
    	letterDao.openCurrentSessionwithTransaction();
    	letterDao.update(entity);
    	letterDao.closeCurrentSessionwithTransaction();
    }
 
    public Letter findById(String id) {
    	letterDao.openCurrentSession();
        Letter letter = letterDao.findById(id);
        letterDao.closeCurrentSession();
        return letter;
    }
 
    public void delete(String id) {
    	letterDao.openCurrentSessionwithTransaction();
        Letter letter = letterDao.findById(id);
        letterDao.delete(letter);
        letterDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Letter> findAll() {
    	letterDao.openCurrentSession();
        List<Letter> letter = letterDao.findAll();
        letterDao.closeCurrentSession();
        return letter;
    }
 
    public void deleteAll() {
    	letterDao.openCurrentSessionwithTransaction();
    	letterDao.deleteAll();
    	letterDao.closeCurrentSessionwithTransaction();
    }
 
    public LetterDao letterDao() {
        return letterDao;
    }
}