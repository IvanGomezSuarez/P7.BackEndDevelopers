package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entities.Letter;

public class LetterDao implements DaoInterface<Letter, String>{
	private Session currentSession;
    
    private Transaction currentTransaction;
 
    public LetterDao() {
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
 
    public void persist(Letter entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Letter entity) {
        getCurrentSession().update(entity);
    }
 
    public Letter findById(String id) {
    	Letter letter = (Letter) getCurrentSession().get(Letter.class, id);
        return letter; 
    }
 
    public void delete(Letter entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<Letter> findAll() {
        List<Letter> letters = (List<Letter>) getCurrentSession().createQuery("from Letter").list();
        return letters;
    }
 
    public void deleteAll() {
        List<Letter> entityList = findAll();
        for (Letter entity : entityList) {
            delete(entity);
        }
    }
}