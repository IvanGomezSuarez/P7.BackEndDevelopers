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
import testmotors.Emd;
import testmotors.Tecnicos;

/**
 *
 * @author Ivan
 */
public class TecnicosJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TecnicosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tecnicos tecnicos) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (tecnicos.getEnsayosCollection() == null) {
            tecnicos.setEnsayosCollection(new ArrayList<Ensayos>());
        }
        if (tecnicos.getEmdCollection() == null) {
            tecnicos.setEmdCollection(new ArrayList<Emd>());
        }
        List<String> illegalOrphanMessages = null;
        Persona personaOrphanCheck = tecnicos.getPersona();
        if (personaOrphanCheck != null) {
            Tecnicos oldTecnicosOfPersona = personaOrphanCheck.getTecnicos();
            if (oldTecnicosOfPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Persona " + personaOrphanCheck + " already has an item of type Tecnicos whose persona column cannot be null. Please make another selection for the persona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persona = tecnicos.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getIdpersona());
                tecnicos.setPersona(persona);
            }
            Collection<Ensayos> attachedEnsayosCollection = new ArrayList<Ensayos>();
            for (Ensayos ensayosCollectionEnsayosToAttach : tecnicos.getEnsayosCollection()) {
                ensayosCollectionEnsayosToAttach = em.getReference(ensayosCollectionEnsayosToAttach.getClass(), ensayosCollectionEnsayosToAttach.getIdensayos());
                attachedEnsayosCollection.add(ensayosCollectionEnsayosToAttach);
            }
            tecnicos.setEnsayosCollection(attachedEnsayosCollection);
            Collection<Emd> attachedEmdCollection = new ArrayList<Emd>();
            for (Emd emdCollectionEmdToAttach : tecnicos.getEmdCollection()) {
                emdCollectionEmdToAttach = em.getReference(emdCollectionEmdToAttach.getClass(), emdCollectionEmdToAttach.getIdemd());
                attachedEmdCollection.add(emdCollectionEmdToAttach);
            }
            tecnicos.setEmdCollection(attachedEmdCollection);
            em.persist(tecnicos);
            if (persona != null) {
                persona.setTecnicos(tecnicos);
                persona = em.merge(persona);
            }
            for (Ensayos ensayosCollectionEnsayos : tecnicos.getEnsayosCollection()) {
                Tecnicos oldEjecutadoPorOfEnsayosCollectionEnsayos = ensayosCollectionEnsayos.getEjecutadoPor();
                ensayosCollectionEnsayos.setEjecutadoPor(tecnicos);
                ensayosCollectionEnsayos = em.merge(ensayosCollectionEnsayos);
                if (oldEjecutadoPorOfEnsayosCollectionEnsayos != null) {
                    oldEjecutadoPorOfEnsayosCollectionEnsayos.getEnsayosCollection().remove(ensayosCollectionEnsayos);
                    oldEjecutadoPorOfEnsayosCollectionEnsayos = em.merge(oldEjecutadoPorOfEnsayosCollectionEnsayos);
                }
            }
            for (Emd emdCollectionEmd : tecnicos.getEmdCollection()) {
                Tecnicos oldTecnicoAsignadoOfEmdCollectionEmd = emdCollectionEmd.getTecnicoAsignado();
                emdCollectionEmd.setTecnicoAsignado(tecnicos);
                emdCollectionEmd = em.merge(emdCollectionEmd);
                if (oldTecnicoAsignadoOfEmdCollectionEmd != null) {
                    oldTecnicoAsignadoOfEmdCollectionEmd.getEmdCollection().remove(emdCollectionEmd);
                    oldTecnicoAsignadoOfEmdCollectionEmd = em.merge(oldTecnicoAsignadoOfEmdCollectionEmd);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTecnicos(tecnicos.getIdtecnicos()) != null) {
                throw new PreexistingEntityException("Tecnicos " + tecnicos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tecnicos tecnicos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnicos persistentTecnicos = em.find(Tecnicos.class, tecnicos.getIdtecnicos());
            Persona personaOld = persistentTecnicos.getPersona();
            Persona personaNew = tecnicos.getPersona();
            Collection<Ensayos> ensayosCollectionOld = persistentTecnicos.getEnsayosCollection();
            Collection<Ensayos> ensayosCollectionNew = tecnicos.getEnsayosCollection();
            Collection<Emd> emdCollectionOld = persistentTecnicos.getEmdCollection();
            Collection<Emd> emdCollectionNew = tecnicos.getEmdCollection();
            List<String> illegalOrphanMessages = null;
            if (personaNew != null && !personaNew.equals(personaOld)) {
                Tecnicos oldTecnicosOfPersona = personaNew.getTecnicos();
                if (oldTecnicosOfPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Persona " + personaNew + " already has an item of type Tecnicos whose persona column cannot be null. Please make another selection for the persona field.");
                }
            }
            for (Ensayos ensayosCollectionOldEnsayos : ensayosCollectionOld) {
                if (!ensayosCollectionNew.contains(ensayosCollectionOldEnsayos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ensayos " + ensayosCollectionOldEnsayos + " since its ejecutadoPor field is not nullable.");
                }
            }
            for (Emd emdCollectionOldEmd : emdCollectionOld) {
                if (!emdCollectionNew.contains(emdCollectionOldEmd)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Emd " + emdCollectionOldEmd + " since its tecnicoAsignado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getIdpersona());
                tecnicos.setPersona(personaNew);
            }
            Collection<Ensayos> attachedEnsayosCollectionNew = new ArrayList<Ensayos>();
            for (Ensayos ensayosCollectionNewEnsayosToAttach : ensayosCollectionNew) {
                ensayosCollectionNewEnsayosToAttach = em.getReference(ensayosCollectionNewEnsayosToAttach.getClass(), ensayosCollectionNewEnsayosToAttach.getIdensayos());
                attachedEnsayosCollectionNew.add(ensayosCollectionNewEnsayosToAttach);
            }
            ensayosCollectionNew = attachedEnsayosCollectionNew;
            tecnicos.setEnsayosCollection(ensayosCollectionNew);
            Collection<Emd> attachedEmdCollectionNew = new ArrayList<Emd>();
            for (Emd emdCollectionNewEmdToAttach : emdCollectionNew) {
                emdCollectionNewEmdToAttach = em.getReference(emdCollectionNewEmdToAttach.getClass(), emdCollectionNewEmdToAttach.getIdemd());
                attachedEmdCollectionNew.add(emdCollectionNewEmdToAttach);
            }
            emdCollectionNew = attachedEmdCollectionNew;
            tecnicos.setEmdCollection(emdCollectionNew);
            tecnicos = em.merge(tecnicos);
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setTecnicos(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.setTecnicos(tecnicos);
                personaNew = em.merge(personaNew);
            }
            for (Ensayos ensayosCollectionNewEnsayos : ensayosCollectionNew) {
                if (!ensayosCollectionOld.contains(ensayosCollectionNewEnsayos)) {
                    Tecnicos oldEjecutadoPorOfEnsayosCollectionNewEnsayos = ensayosCollectionNewEnsayos.getEjecutadoPor();
                    ensayosCollectionNewEnsayos.setEjecutadoPor(tecnicos);
                    ensayosCollectionNewEnsayos = em.merge(ensayosCollectionNewEnsayos);
                    if (oldEjecutadoPorOfEnsayosCollectionNewEnsayos != null && !oldEjecutadoPorOfEnsayosCollectionNewEnsayos.equals(tecnicos)) {
                        oldEjecutadoPorOfEnsayosCollectionNewEnsayos.getEnsayosCollection().remove(ensayosCollectionNewEnsayos);
                        oldEjecutadoPorOfEnsayosCollectionNewEnsayos = em.merge(oldEjecutadoPorOfEnsayosCollectionNewEnsayos);
                    }
                }
            }
            for (Emd emdCollectionNewEmd : emdCollectionNew) {
                if (!emdCollectionOld.contains(emdCollectionNewEmd)) {
                    Tecnicos oldTecnicoAsignadoOfEmdCollectionNewEmd = emdCollectionNewEmd.getTecnicoAsignado();
                    emdCollectionNewEmd.setTecnicoAsignado(tecnicos);
                    emdCollectionNewEmd = em.merge(emdCollectionNewEmd);
                    if (oldTecnicoAsignadoOfEmdCollectionNewEmd != null && !oldTecnicoAsignadoOfEmdCollectionNewEmd.equals(tecnicos)) {
                        oldTecnicoAsignadoOfEmdCollectionNewEmd.getEmdCollection().remove(emdCollectionNewEmd);
                        oldTecnicoAsignadoOfEmdCollectionNewEmd = em.merge(oldTecnicoAsignadoOfEmdCollectionNewEmd);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tecnicos.getIdtecnicos();
                if (findTecnicos(id) == null) {
                    throw new NonexistentEntityException("The tecnicos with id " + id + " no longer exists.");
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
            Tecnicos tecnicos;
            try {
                tecnicos = em.getReference(Tecnicos.class, id);
                tecnicos.getIdtecnicos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tecnicos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ensayos> ensayosCollectionOrphanCheck = tecnicos.getEnsayosCollection();
            for (Ensayos ensayosCollectionOrphanCheckEnsayos : ensayosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tecnicos (" + tecnicos + ") cannot be destroyed since the Ensayos " + ensayosCollectionOrphanCheckEnsayos + " in its ensayosCollection field has a non-nullable ejecutadoPor field.");
            }
            Collection<Emd> emdCollectionOrphanCheck = tecnicos.getEmdCollection();
            for (Emd emdCollectionOrphanCheckEmd : emdCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tecnicos (" + tecnicos + ") cannot be destroyed since the Emd " + emdCollectionOrphanCheckEmd + " in its emdCollection field has a non-nullable tecnicoAsignado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona persona = tecnicos.getPersona();
            if (persona != null) {
                persona.setTecnicos(null);
                persona = em.merge(persona);
            }
            em.remove(tecnicos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tecnicos> findTecnicosEntities() {
        return findTecnicosEntities(true, -1, -1);
    }

    public List<Tecnicos> findTecnicosEntities(int maxResults, int firstResult) {
        return findTecnicosEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Tecnicos> findTecnicosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tecnicos.class));
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

    public Tecnicos findTecnicos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tecnicos.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getTecnicosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tecnicos> rt = cq.from(Tecnicos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
