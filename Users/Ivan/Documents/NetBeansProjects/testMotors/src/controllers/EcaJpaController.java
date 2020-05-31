/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import testmotors.Eca;
import testmotors.Ensayos;

/**
 *
 * @author Ivan
 */
@SuppressWarnings("serial")
public class EcaJpaController implements Serializable {

    public EcaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eca eca) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ensayos idensayo = eca.getIdensayo();
            if (idensayo != null) {
                idensayo = em.getReference(idensayo.getClass(), idensayo.getIdensayos());
                eca.setIdensayo(idensayo);
            }
            em.persist(eca);
            if (idensayo != null) {
                idensayo.getEcaCollection().add(eca);
                idensayo = em.merge(idensayo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEca(eca.getIdeca()) != null) {
                throw new PreexistingEntityException("Eca " + eca + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Eca eca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eca persistentEca = em.find(Eca.class, eca.getIdeca());
            Ensayos idensayoOld = persistentEca.getIdensayo();
            Ensayos idensayoNew = eca.getIdensayo();
            if (idensayoNew != null) {
                idensayoNew = em.getReference(idensayoNew.getClass(), idensayoNew.getIdensayos());
                eca.setIdensayo(idensayoNew);
            }
            eca = em.merge(eca);
            if (idensayoOld != null && !idensayoOld.equals(idensayoNew)) {
                idensayoOld.getEcaCollection().remove(eca);
                idensayoOld = em.merge(idensayoOld);
            }
            if (idensayoNew != null && !idensayoNew.equals(idensayoOld)) {
                idensayoNew.getEcaCollection().add(eca);
                idensayoNew = em.merge(idensayoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eca.getIdeca();
                if (findEca(id) == null) {
                    throw new NonexistentEntityException("The eca with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eca eca;
            try {
                eca = em.getReference(Eca.class, id);
                eca.getIdeca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eca with id " + id + " no longer exists.", enfe);
            }
            Ensayos idensayo = eca.getIdensayo();
            if (idensayo != null) {
                idensayo.getEcaCollection().remove(eca);
                idensayo = em.merge(idensayo);
            }
            em.remove(eca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eca> findEcaEntities() {
        return findEcaEntities(true, -1, -1);
    }

    public List<Eca> findEcaEntities(int maxResults, int firstResult) {
        return findEcaEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Eca> findEcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eca.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Eca findEca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eca.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getEcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eca> rt = cq.from(Eca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
