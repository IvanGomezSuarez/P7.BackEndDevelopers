package service;

import java.util.List;

import dao.GameDao;
import entities.Game;

public class GameService {
	 
    private static GameDao gameDao;
 
    public GameService() {
        gameDao = new GameDao();
    }
 
    public void persist(Game entity) {
    	gameDao.openCurrentSessionwithTransaction();
    	gameDao.persist(entity);
    	gameDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Game entity) {
    	gameDao.openCurrentSessionwithTransaction();
    	gameDao.update(entity);
    	gameDao.closeCurrentSessionwithTransaction();
    }
 
    public Game findById(String id) {
    	gameDao.openCurrentSession();
        Game game = gameDao.findById(id);
        gameDao.closeCurrentSession();
        return game;
    }
 
    public void delete(String id) {
    	gameDao.openCurrentSessionwithTransaction();
        Game game = gameDao.findById(id);
        gameDao.delete(game);
        gameDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Game> findAll() {
    	gameDao.openCurrentSession();
        List<Game> game = gameDao.findAll();
        gameDao.closeCurrentSession();
        return game;
    }
 
    public void deleteAll() {
    	gameDao.openCurrentSessionwithTransaction();
    	gameDao.deleteAll();
    	gameDao.closeCurrentSessionwithTransaction();
    }
 
    public GameDao bookDao() {
        return gameDao;
    }
}