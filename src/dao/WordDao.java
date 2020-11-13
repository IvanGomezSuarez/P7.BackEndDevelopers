package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entities.Word;

public class WordDao implements DaoInterface<Word, String>{
	private Session currentSession;
    
    private Transaction currentTransaction;
 
    public WordDao() {
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
 
    public void persist(Word entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Word entity) {
        getCurrentSession().update(entity);
    }
 
    public Word findById(String id) {
    	Word word = (Word) getCurrentSession().get(Word.class, id);
        return word; 
    }
 
    public void delete(Word entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<Word> findAll() {
        List<Word> words = (List<Word>) getCurrentSession().createQuery("from Word").list();
        return words;
    }
 
    public void deleteAll() {
        List<Word> entityList = findAll();
        for (Word entity : entityList) {
            delete(entity);
        }
    }
}