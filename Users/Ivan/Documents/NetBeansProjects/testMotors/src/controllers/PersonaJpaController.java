/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import testmotors.Tecnicos;
import testmotors.Clientes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import testmotors.Persona;

/**
 *
 * @author Ivan
 */
public class PersonaJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) {
        if (persona.getClientesCollection() == null) {
            persona.setClientesCollection(new ArrayList<Clientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnicos tecnicos = persona.getTecnicos();
            if (tecnicos != null) {
                tecnicos = em.getReference(tecnicos.getClass(), tecnicos.getIdtecnicos());
                persona.setTecnicos(tecnicos);
            }
            Collection<Clientes> attachedClientesCollection = new ArrayList<Clientes>();
            for (Clientes clientesCollectionClientesToAttach : persona.getClientesCollection()) {
                clientesCollectionClientesToAttach = em.getReference(clientesCollectionClientesToAttach.getClass(), clientesCollectionClientesToAttach.getIdclientes());
                attachedClientesCollection.add(clientesCollectionClientesToAttach);
            }
            persona.setClientesCollection(attachedClientesCollection);
            em.persist(persona);
            if (tecnicos != null) {
                Persona oldPersonaOfTecnicos = tecnicos.getPersona();
                if (oldPersonaOfTecnicos != null) {
                    oldPersonaOfTecnicos.setTecnicos(null);
                    oldPersonaOfTecnicos = em.merge(oldPersonaOfTecnicos);
                }
                tecnicos.setPersona(persona);
                tecnicos = em.merge(tecnicos);
            }
            for (Clientes clientesCollectionClientes : persona.getClientesCollection()) {
                Persona oldPContactoOfClientesCollectionClientes = clientesCollectionClientes.getPContacto();
                clientesCollectionClientes.setPContacto(persona);
                clientesCollectionClientes = em.merge(clientesCollectionClientes);
                if (oldPContactoOfClientesCollectionClientes != null) {
                    oldPContactoOfClientesCollectionClientes.getClientesCollection().remove(clientesCollectionClientes);
                    oldPContactoOfClientesCollectionClientes = em.merge(oldPContactoOfClientesCollectionClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getIdpersona());
            Tecnicos tecnicosOld = persistentPersona.getTecnicos();
            Tecnicos tecnicosNew = persona.getTecnicos();
            Collection<Clientes> clientesCollectionOld = persistentPersona.getClientesCollection();
            Collection<Clientes> clientesCollectionNew = persona.getClientesCollection();
            List<String> illegalOrphanMessages = null;
            if (tecnicosOld != null && !tecnicosOld.equals(tecnicosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Tecnicos " + tecnicosOld + " since its persona field is not nullable.");
            }
            for (Clientes clientesCollectionOldClientes : clientesCollectionOld) {
                if (!clientesCollectionNew.contains(clientesCollectionOldClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Clientes " + clientesCollectionOldClientes + " since its PContacto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tecnicosNew != null) {
                tecnicosNew = em.getReference(tecnicosNew.getClass(), tecnicosNew.getIdtecnicos());
                persona.setTecnicos(tecnicosNew);
            }
            Collection<Clientes> attachedClientesCollectionNew = new ArrayList<Clientes>();
            for (Clientes clientesCollectionNewClientesToAttach : clientesCollectionNew) {
                clientesCollectionNewClientesToAttach = em.getReference(clientesCollectionNewClientesToAttach.getClass(), clientesCollectionNewClientesToAttach.getIdclientes());
                attachedClientesCollectionNew.add(clientesCollectionNewClientesToAttach);
            }
            clientesCollectionNew = attachedClientesCollectionNew;
            persona.setClientesCollection(clientesCollectionNew);
            persona = em.merge(persona);
            if (tecnicosNew != null && !tecnicosNew.equals(tecnicosOld)) {
                Persona oldPersonaOfTecnicos = tecnicosNew.getPersona();
                if (oldPersonaOfTecnicos != null) {
                    oldPersonaOfTecnicos.setTecnicos(null);
                    oldPersonaOfTecnicos = em.merge(oldPersonaOfTecnicos);
                }
                tecnicosNew.setPersona(persona);
                tecnicosNew = em.merge(tecnicosNew);
            }
            for (Clientes clientesCollectionNewClientes : clientesCollectionNew) {
                if (!clientesCollectionOld.contains(clientesCollectionNewClientes)) {
                    Persona oldPContactoOfClientesCollectionNewClientes = clientesCollectionNewClientes.getPContacto();
                    clientesCollectionNewClientes.setPContacto(persona);
                    clientesCollectionNewClientes = em.merge(clientesCollectionNewClientes);
                    if (oldPContactoOfClientesCollectionNewClientes != null && !oldPContactoOfClientesCollectionNewClientes.equals(persona)) {
                        oldPContactoOfClientesCollectionNewClientes.getClientesCollection().remove(clientesCollectionNewClientes);
                        oldPContactoOfClientesCollectionNewClientes = em.merge(oldPContactoOfClientesCollectionNewClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getIdpersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Tecnicos tecnicosOrphanCheck = persona.getTecnicos();
            if (tecnicosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Tecnicos " + tecnicosOrphanCheck + " in its tecnicos field has a non-nullable persona field.");
            }
            Collection<Clientes> clientesCollectionOrphanCheck = persona.getClientesCollection();
            for (Clientes clientesCollectionOrphanCheckClientes : clientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Clientes " + clientesCollectionOrphanCheckClientes + " in its clientesCollection field has a non-nullable PContacto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
