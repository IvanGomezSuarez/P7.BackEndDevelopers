package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entities.Game;

public class GameDao implements DaoInterface<Game, String>{
	private Session currentSession;
    
    private Transaction currentTransaction;
 
    public GameDao() {
    }
 
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
 
    public void persist(Game entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Game entity) {
        getCurrentSession().update(entity);
    }
 
    public Game findById(String id) {
    	Game game = (Game) getCurrentSession().get(Game.class, id);
        return game; 
    }
 
    public void delete(Game entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<Game> findAll() {
        List<Game> games = (List<Game>) getCurrentSession().createQuery("from Game").list();
        return games;
    }
 
    public void deleteAll() {
        List<Game> entityList = findAll();
        for (Game entity : entityList) {
            delete(entity);
        }
    }
}