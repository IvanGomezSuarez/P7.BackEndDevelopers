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
import testmotors.Emd;
import testmotors.Tecnicos;

/**
 *
 * @author Ivan
 */
public class EmdJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmdJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emd emd) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnicos tecnicoAsignado = emd.getTecnicoAsignado();
            if (tecnicoAsignado != null) {
                tecnicoAsignado = em.getReference(tecnicoAsignado.getClass(), tecnicoAsignado.getIdtecnicos());
                emd.setTecnicoAsignado(tecnicoAsignado);
            }
            em.persist(emd);
            if (tecnicoAsignado != null) {
                tecnicoAsignado.getEmdCollection().add(emd);
                tecnicoAsignado = em.merge(tecnicoAsignado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmd(emd.getIdemd()) != null) {
                throw new PreexistingEntityException("Emd " + emd + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emd emd) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emd persistentEmd = em.find(Emd.class, emd.getIdemd());
            Tecnicos tecnicoAsignadoOld = persistentEmd.getTecnicoAsignado();
            Tecnicos tecnicoAsignadoNew = emd.getTecnicoAsignado();
            if (tecnicoAsignadoNew != null) {
                tecnicoAsignadoNew = em.getReference(tecnicoAsignadoNew.getClass(), tecnicoAsignadoNew.getIdtecnicos());
                emd.setTecnicoAsignado(tecnicoAsignadoNew);
            }
            emd = em.merge(emd);
            if (tecnicoAsignadoOld != null && !tecnicoAsignadoOld.equals(tecnicoAsignadoNew)) {
                tecnicoAsignadoOld.getEmdCollection().remove(emd);
                tecnicoAsignadoOld = em.merge(tecnicoAsignadoOld);
            }
            if (tecnicoAsignadoNew != null && !tecnicoAsignadoNew.equals(tecnicoAsignadoOld)) {
                tecnicoAsignadoNew.getEmdCollection().add(emd);
                tecnicoAsignadoNew = em.merge(tecnicoAsignadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = emd.getIdemd();
                if (findEmd(id) == null) {
                    throw new NonexistentEntityException("The emd with id " + id + " no longer exists.");
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
            Emd emd;
            try {
                emd = em.getReference(Emd.class, id);
                emd.getIdemd();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emd with id " + id + " no longer exists.", enfe);
            }
            Tecnicos tecnicoAsignado = emd.getTecnicoAsignado();
            if (tecnicoAsignado != null) {
                tecnicoAsignado.getEmdCollection().remove(emd);
                tecnicoAsignado = em.merge(tecnicoAsignado);
            }
            em.remove(emd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emd> findEmdEntities() {
        return findEmdEntities(true, -1, -1);
    }

    public List<Emd> findEmdEntities(int maxResults, int firstResult) {
        return findEmdEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Emd> findEmdEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emd.class));
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

    public Emd findEmd(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emd.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getEmdCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emd> rt = cq.from(Emd.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
