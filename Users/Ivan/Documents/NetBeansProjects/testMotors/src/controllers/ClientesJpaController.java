/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import testmotors.Persona;
import testmotors.Ensayos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import testmotors.Clientes;

/**
 *
 * @author Ivan
 */
public class ClientesJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) throws PreexistingEntityException, Exception {
        if (clientes.getEnsayosCollection() == null) {
            clientes.setEnsayosCollection(new ArrayList<Ensayos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona PContacto = clientes.getPContacto();
            if (PContacto != null) {
                PContacto = em.getReference(PContacto.getClass(), PContacto.getIdpersona());
                clientes.setPContacto(PContacto);
            }
            Collection<Ensayos> attachedEnsayosCollection = new ArrayList<Ensayos>();
            for (Ensayos ensayosCollectionEnsayosToAttach : clientes.getEnsayosCollection()) {
                ensayosCollectionEnsayosToAttach = em.getReference(ensayosCollectionEnsayosToAttach.getClass(), ensayosCollectionEnsayosToAttach.getIdensayos());
                attachedEnsayosCollection.add(ensayosCollectionEnsayosToAttach);
            }
            clientes.setEnsayosCollection(attachedEnsayosCollection);
            em.persist(clientes);
            if (PContacto != null) {
                PContacto.getClientesCollection().add(clientes);
                PContacto = em.merge(PContacto);
            }
            for (Ensayos ensayosCollectionEnsayos : clientes.getEnsayosCollection()) {
                Clientes oldClienteOfEnsayosCollectionEnsayos = ensayosCollectionEnsayos.getCliente();
                ensayosCollectionEnsayos.setCliente(clientes);
                ensayosCollectionEnsayos = em.merge(ensayosCollectionEnsayos);
                if (oldClienteOfEnsayosCollectionEnsayos != null) {
                    oldClienteOfEnsayosCollectionEnsayos.getEnsayosCollection().remove(ensayosCollectionEnsayos);
                    oldClienteOfEnsayosCollectionEnsayos = em.merge(oldClienteOfEnsayosCollectionEnsayos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientes(clientes.getIdclientes()) != null) {
                throw new PreexistingEntityException("Clientes " + clientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getIdclientes());
            Persona PContactoOld = persistentClientes.getPContacto();
            Persona PContactoNew = clientes.getPContacto();
            Collection<Ensayos> ensayosCollectionOld = persistentClientes.getEnsayosCollection();
            Collection<Ensayos> ensayosCollectionNew = clientes.getEnsayosCollection();
            List<String> illegalOrphanMessages = null;
            for (Ensayos ensayosCollectionOldEnsayos : ensayosCollectionOld) {
                if (!ensayosCollectionNew.contains(ensayosCollectionOldEnsayos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ensayos " + ensayosCollectionOldEnsayos + " since its cliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (PContactoNew != null) {
                PContactoNew = em.getReference(PContactoNew.getClass(), PContactoNew.getIdpersona());
                clientes.setPContacto(PContactoNew);
            }
            Collection<Ensayos> attachedEnsayosCollectionNew = new ArrayList<Ensayos>();
            for (Ensayos ensayosCollectionNewEnsayosToAttach : ensayosCollectionNew) {
                ensayosCollectionNewEnsayosToAttach = em.getReference(ensayosCollectionNewEnsayosToAttach.getClass(), ensayosCollectionNewEnsayosToAttach.getIdensayos());
                attachedEnsayosCollectionNew.add(ensayosCollectionNewEnsayosToAttach);
            }
            ensayosCollectionNew = attachedEnsayosCollectionNew;
            clientes.setEnsayosCollection(ensayosCollectionNew);
            clientes = em.merge(clientes);
            if (PContactoOld != null && !PContactoOld.equals(PContactoNew)) {
                PContactoOld.getClientesCollection().remove(clientes);
                PContactoOld = em.merge(PContactoOld);
            }
            if (PContactoNew != null && !PContactoNew.equals(PContactoOld)) {
                PContactoNew.getClientesCollection().add(clientes);
                PContactoNew = em.merge(PContactoNew);
            }
            for (Ensayos ensayosCollectionNewEnsayos : ensayosCollectionNew) {
                if (!ensayosCollectionOld.contains(ensayosCollectionNewEnsayos)) {
                    Clientes oldClienteOfEnsayosCollectionNewEnsayos = ensayosCollectionNewEnsayos.getCliente();
                    ensayosCollectionNewEnsayos.setCliente(clientes);
                    ensayosCollectionNewEnsayos = em.merge(ensayosCollectionNewEnsayos);
                    if (oldClienteOfEnsayosCollectionNewEnsayos != null && !oldClienteOfEnsayosCollectionNewEnsayos.equals(clientes)) {
                        oldClienteOfEnsayosCollectionNewEnsayos.getEnsayosCollection().remove(ensayosCollectionNewEnsayos);
                        oldClienteOfEnsayosCollectionNewEnsayos = em.merge(oldClienteOfEnsayosCollectionNewEnsayos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getIdclientes();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getIdclientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ensayos> ensayosCollectionOrphanCheck = clientes.getEnsayosCollection();
            for (Ensayos ensayosCollectionOrphanCheckEnsayos : ensayosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Ensayos " + ensayosCollectionOrphanCheckEnsayos + " in its ensayosCollection field has a non-nullable cliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona PContacto = clientes.getPContacto();
            if (PContacto != null) {
                PContacto.getClientesCollection().remove(clientes);
                PContacto = em.merge(PContacto);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
	public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            @SuppressWarnings("rawtypes")
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
