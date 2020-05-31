/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import testmotors.Ensayos;
import testmotors.Mca;

/**
 *
 * @author Ivan
 */
public class McaJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public McaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mca mca) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ensayos idEnsayo = mca.getIdEnsayo();
            if (idEnsayo != null) {
                idEnsayo = em.getReference(idEnsayo.getClass(), idEnsayo.getIdensayos());
                mca.setIdEnsayo(idEnsayo);
            }
            em.persist(mca);
            if (idEnsayo != null) {
                idEnsayo.getMcaCollection().add(mca);
                idEnsayo = em.merge(idEnsayo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mca mca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mca persistentMca = em.find(Mca.class, mca.getIdmca());
            Ensayos idEnsayoOld = persistentMca.getIdEnsayo();
            Ensayos idEnsayoNew = mca.getIdEnsayo();
            if (idEnsayoNew != null) {
                idEnsayoNew = em.getReference(idEnsayoNew.getClass(), idEnsayoNew.getIdensayos());
                mca.setIdEnsayo(idEnsayoNew);
            }
            mca = em.merge(mca);
            if (idEnsayoOld != null && !idEnsayoOld.equals(idEnsayoNew)) {
                idEnsayoOld.getMcaCollection().remove(mca);
                idEnsayoOld = em.merge(idEnsayoOld);
            }
            if (idEnsayoNew != null && !idEnsayoNew.equals(idEnsayoOld)) {
                idEnsayoNew.getMcaCollection().add(mca);
                idEnsayoNew = em.merge(idEnsayoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mca.getIdmca();
                if (findMca(id) == null) {
                    throw new NonexistentEntityException("The mca with id " + id + " no longer exists.");
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
            Mca mca;
            try {
                mca = em.getReference(Mca.class, id);
                mca.getIdmca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mca with id " + id + " no longer exists.", enfe);
            }
            Ensayos idEnsayo = mca.getIdEnsayo();
            if (idEnsayo != null) {
                idEnsayo.getMcaCollection().remove(mca);
                idEnsayo = em.merge(idEnsayo);
            }
            em.remove(mca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mca> findMcaEntities() {
        return findMcaEntities(true, -1, -1);
    }

    public List<Mca> findMcaEntities(int maxResults, int firstResult) {
        return findMcaEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Mca> findMcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mca.class));
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

    public Mca findMca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mca.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getMcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mca> rt = cq.from(Mca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
