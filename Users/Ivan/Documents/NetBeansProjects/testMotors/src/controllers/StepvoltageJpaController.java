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
import testmotors.Ensayos;
import testmotors.Stepvoltage;

/**
 *
 * @author Ivan
 */
public class StepvoltageJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StepvoltageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Stepvoltage stepvoltage) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ensayos idEnsayo = stepvoltage.getIdEnsayo();
            if (idEnsayo != null) {
                idEnsayo = em.getReference(idEnsayo.getClass(), idEnsayo.getIdensayos());
                stepvoltage.setIdEnsayo(idEnsayo);
            }
            em.persist(stepvoltage);
            if (idEnsayo != null) {
                idEnsayo.getStepvoltageCollection().add(stepvoltage);
                idEnsayo = em.merge(idEnsayo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStepvoltage(stepvoltage.getIdstepVoltage()) != null) {
                throw new PreexistingEntityException("Stepvoltage " + stepvoltage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Stepvoltage stepvoltage) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stepvoltage persistentStepvoltage = em.find(Stepvoltage.class, stepvoltage.getIdstepVoltage());
            Ensayos idEnsayoOld = persistentStepvoltage.getIdEnsayo();
            Ensayos idEnsayoNew = stepvoltage.getIdEnsayo();
            if (idEnsayoNew != null) {
                idEnsayoNew = em.getReference(idEnsayoNew.getClass(), idEnsayoNew.getIdensayos());
                stepvoltage.setIdEnsayo(idEnsayoNew);
            }
            stepvoltage = em.merge(stepvoltage);
            if (idEnsayoOld != null && !idEnsayoOld.equals(idEnsayoNew)) {
                idEnsayoOld.getStepvoltageCollection().remove(stepvoltage);
                idEnsayoOld = em.merge(idEnsayoOld);
            }
            if (idEnsayoNew != null && !idEnsayoNew.equals(idEnsayoOld)) {
                idEnsayoNew.getStepvoltageCollection().add(stepvoltage);
                idEnsayoNew = em.merge(idEnsayoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stepvoltage.getIdstepVoltage();
                if (findStepvoltage(id) == null) {
                    throw new NonexistentEntityException("The stepvoltage with id " + id + " no longer exists.");
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
            Stepvoltage stepvoltage;
            try {
                stepvoltage = em.getReference(Stepvoltage.class, id);
                stepvoltage.getIdstepVoltage();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stepvoltage with id " + id + " no longer exists.", enfe);
            }
            Ensayos idEnsayo = stepvoltage.getIdEnsayo();
            if (idEnsayo != null) {
                idEnsayo.getStepvoltageCollection().remove(stepvoltage);
                idEnsayo = em.merge(idEnsayo);
            }
            em.remove(stepvoltage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Stepvoltage> findStepvoltageEntities() {
        return findStepvoltageEntities(true, -1, -1);
    }

    public List<Stepvoltage> findStepvoltageEntities(int maxResults, int firstResult) {
        return findStepvoltageEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Stepvoltage> findStepvoltageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Stepvoltage.class));
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

    public Stepvoltage findStepvoltage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Stepvoltage.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getStepvoltageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Stepvoltage> rt = cq.from(Stepvoltage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
