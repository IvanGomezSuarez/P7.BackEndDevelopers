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
import testmotors.Clientes;
import testmotors.Tecnicos;
import testmotors.Eca;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import testmotors.Ensayos;
import testmotors.Mca;
import testmotors.Stepvoltage;

/**
 *
 * @author Ivan
 */
public class EnsayosJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnsayosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ensayos ensayos) throws PreexistingEntityException, Exception {
        if (ensayos.getEcaCollection() == null) {
            ensayos.setEcaCollection(new ArrayList<Eca>());
        }
        if (ensayos.getMcaCollection() == null) {
            ensayos.setMcaCollection(new ArrayList<Mca>());
        }
        if (ensayos.getStepvoltageCollection() == null) {
            ensayos.setStepvoltageCollection(new ArrayList<Stepvoltage>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes cliente = ensayos.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdclientes());
                ensayos.setCliente(cliente);
            }
            Tecnicos ejecutadoPor = ensayos.getEjecutadoPor();
            if (ejecutadoPor != null) {
                ejecutadoPor = em.getReference(ejecutadoPor.getClass(), ejecutadoPor.getIdtecnicos());
                ensayos.setEjecutadoPor(ejecutadoPor);
            }
            Collection<Eca> attachedEcaCollection = new ArrayList<Eca>();
            for (Eca ecaCollectionEcaToAttach : ensayos.getEcaCollection()) {
                ecaCollectionEcaToAttach = em.getReference(ecaCollectionEcaToAttach.getClass(), ecaCollectionEcaToAttach.getIdeca());
                attachedEcaCollection.add(ecaCollectionEcaToAttach);
            }
            ensayos.setEcaCollection(attachedEcaCollection);
            Collection<Mca> attachedMcaCollection = new ArrayList<Mca>();
            for (Mca mcaCollectionMcaToAttach : ensayos.getMcaCollection()) {
                mcaCollectionMcaToAttach = em.getReference(mcaCollectionMcaToAttach.getClass(), mcaCollectionMcaToAttach.getIdmca());
                attachedMcaCollection.add(mcaCollectionMcaToAttach);
            }
            ensayos.setMcaCollection(attachedMcaCollection);
            Collection<Stepvoltage> attachedStepvoltageCollection = new ArrayList<Stepvoltage>();
            for (Stepvoltage stepvoltageCollectionStepvoltageToAttach : ensayos.getStepvoltageCollection()) {
                stepvoltageCollectionStepvoltageToAttach = em.getReference(stepvoltageCollectionStepvoltageToAttach.getClass(), stepvoltageCollectionStepvoltageToAttach.getIdstepVoltage());
                attachedStepvoltageCollection.add(stepvoltageCollectionStepvoltageToAttach);
            }
            ensayos.setStepvoltageCollection(attachedStepvoltageCollection);
            em.persist(ensayos);
            if (cliente != null) {
                cliente.getEnsayosCollection().add(ensayos);
                cliente = em.merge(cliente);
            }
            if (ejecutadoPor != null) {
                ejecutadoPor.getEnsayosCollection().add(ensayos);
                ejecutadoPor = em.merge(ejecutadoPor);
            }
            for (Eca ecaCollectionEca : ensayos.getEcaCollection()) {
                Ensayos oldIdensayoOfEcaCollectionEca = ecaCollectionEca.getIdensayo();
                ecaCollectionEca.setIdensayo(ensayos);
                ecaCollectionEca = em.merge(ecaCollectionEca);
                if (oldIdensayoOfEcaCollectionEca != null) {
                    oldIdensayoOfEcaCollectionEca.getEcaCollection().remove(ecaCollectionEca);
                    oldIdensayoOfEcaCollectionEca = em.merge(oldIdensayoOfEcaCollectionEca);
                }
            }
            for (Mca mcaCollectionMca : ensayos.getMcaCollection()) {
                Ensayos oldIdEnsayoOfMcaCollectionMca = mcaCollectionMca.getIdEnsayo();
                mcaCollectionMca.setIdEnsayo(ensayos);
                mcaCollectionMca = em.merge(mcaCollectionMca);
                if (oldIdEnsayoOfMcaCollectionMca != null) {
                    oldIdEnsayoOfMcaCollectionMca.getMcaCollection().remove(mcaCollectionMca);
                    oldIdEnsayoOfMcaCollectionMca = em.merge(oldIdEnsayoOfMcaCollectionMca);
                }
            }
            for (Stepvoltage stepvoltageCollectionStepvoltage : ensayos.getStepvoltageCollection()) {
                Ensayos oldIdEnsayoOfStepvoltageCollectionStepvoltage = stepvoltageCollectionStepvoltage.getIdEnsayo();
                stepvoltageCollectionStepvoltage.setIdEnsayo(ensayos);
                stepvoltageCollectionStepvoltage = em.merge(stepvoltageCollectionStepvoltage);
                if (oldIdEnsayoOfStepvoltageCollectionStepvoltage != null) {
                    oldIdEnsayoOfStepvoltageCollectionStepvoltage.getStepvoltageCollection().remove(stepvoltageCollectionStepvoltage);
                    oldIdEnsayoOfStepvoltageCollectionStepvoltage = em.merge(oldIdEnsayoOfStepvoltageCollectionStepvoltage);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnsayos(ensayos.getIdensayos()) != null) {
                throw new PreexistingEntityException("Ensayos " + ensayos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ensayos ensayos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ensayos persistentEnsayos = em.find(Ensayos.class, ensayos.getIdensayos());
            Clientes clienteOld = persistentEnsayos.getCliente();
            Clientes clienteNew = ensayos.getCliente();
            Tecnicos ejecutadoPorOld = persistentEnsayos.getEjecutadoPor();
            Tecnicos ejecutadoPorNew = ensayos.getEjecutadoPor();
            Collection<Eca> ecaCollectionOld = persistentEnsayos.getEcaCollection();
            Collection<Eca> ecaCollectionNew = ensayos.getEcaCollection();
            Collection<Mca> mcaCollectionOld = persistentEnsayos.getMcaCollection();
            Collection<Mca> mcaCollectionNew = ensayos.getMcaCollection();
            Collection<Stepvoltage> stepvoltageCollectionOld = persistentEnsayos.getStepvoltageCollection();
            Collection<Stepvoltage> stepvoltageCollectionNew = ensayos.getStepvoltageCollection();
            List<String> illegalOrphanMessages = null;
            for (Eca ecaCollectionOldEca : ecaCollectionOld) {
                if (!ecaCollectionNew.contains(ecaCollectionOldEca)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Eca " + ecaCollectionOldEca + " since its idensayo field is not nullable.");
                }
            }
            for (Mca mcaCollectionOldMca : mcaCollectionOld) {
                if (!mcaCollectionNew.contains(mcaCollectionOldMca)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mca " + mcaCollectionOldMca + " since its idEnsayo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdclientes());
                ensayos.setCliente(clienteNew);
            }
            if (ejecutadoPorNew != null) {
                ejecutadoPorNew = em.getReference(ejecutadoPorNew.getClass(), ejecutadoPorNew.getIdtecnicos());
                ensayos.setEjecutadoPor(ejecutadoPorNew);
            }
            Collection<Eca> attachedEcaCollectionNew = new ArrayList<Eca>();
            for (Eca ecaCollectionNewEcaToAttach : ecaCollectionNew) {
                ecaCollectionNewEcaToAttach = em.getReference(ecaCollectionNewEcaToAttach.getClass(), ecaCollectionNewEcaToAttach.getIdeca());
                attachedEcaCollectionNew.add(ecaCollectionNewEcaToAttach);
            }
            ecaCollectionNew = attachedEcaCollectionNew;
            ensayos.setEcaCollection(ecaCollectionNew);
            Collection<Mca> attachedMcaCollectionNew = new ArrayList<Mca>();
            for (Mca mcaCollectionNewMcaToAttach : mcaCollectionNew) {
                mcaCollectionNewMcaToAttach = em.getReference(mcaCollectionNewMcaToAttach.getClass(), mcaCollectionNewMcaToAttach.getIdmca());
                attachedMcaCollectionNew.add(mcaCollectionNewMcaToAttach);
            }
            mcaCollectionNew = attachedMcaCollectionNew;
            ensayos.setMcaCollection(mcaCollectionNew);
            Collection<Stepvoltage> attachedStepvoltageCollectionNew = new ArrayList<Stepvoltage>();
            for (Stepvoltage stepvoltageCollectionNewStepvoltageToAttach : stepvoltageCollectionNew) {
                stepvoltageCollectionNewStepvoltageToAttach = em.getReference(stepvoltageCollectionNewStepvoltageToAttach.getClass(), stepvoltageCollectionNewStepvoltageToAttach.getIdstepVoltage());
                attachedStepvoltageCollectionNew.add(stepvoltageCollectionNewStepvoltageToAttach);
            }
            stepvoltageCollectionNew = attachedStepvoltageCollectionNew;
            ensayos.setStepvoltageCollection(stepvoltageCollectionNew);
            ensayos = em.merge(ensayos);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getEnsayosCollection().remove(ensayos);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getEnsayosCollection().add(ensayos);
                clienteNew = em.merge(clienteNew);
            }
            if (ejecutadoPorOld != null && !ejecutadoPorOld.equals(ejecutadoPorNew)) {
                ejecutadoPorOld.getEnsayosCollection().remove(ensayos);
                ejecutadoPorOld = em.merge(ejecutadoPorOld);
            }
            if (ejecutadoPorNew != null && !ejecutadoPorNew.equals(ejecutadoPorOld)) {
                ejecutadoPorNew.getEnsayosCollection().add(ensayos);
                ejecutadoPorNew = em.merge(ejecutadoPorNew);
            }
            for (Eca ecaCollectionNewEca : ecaCollectionNew) {
                if (!ecaCollectionOld.contains(ecaCollectionNewEca)) {
                    Ensayos oldIdensayoOfEcaCollectionNewEca = ecaCollectionNewEca.getIdensayo();
                    ecaCollectionNewEca.setIdensayo(ensayos);
                    ecaCollectionNewEca = em.merge(ecaCollectionNewEca);
                    if (oldIdensayoOfEcaCollectionNewEca != null && !oldIdensayoOfEcaCollectionNewEca.equals(ensayos)) {
                        oldIdensayoOfEcaCollectionNewEca.getEcaCollection().remove(ecaCollectionNewEca);
                        oldIdensayoOfEcaCollectionNewEca = em.merge(oldIdensayoOfEcaCollectionNewEca);
                    }
                }
            }
            for (Mca mcaCollectionNewMca : mcaCollectionNew) {
                if (!mcaCollectionOld.contains(mcaCollectionNewMca)) {
                    Ensayos oldIdEnsayoOfMcaCollectionNewMca = mcaCollectionNewMca.getIdEnsayo();
                    mcaCollectionNewMca.setIdEnsayo(ensayos);
                    mcaCollectionNewMca = em.merge(mcaCollectionNewMca);
                    if (oldIdEnsayoOfMcaCollectionNewMca != null && !oldIdEnsayoOfMcaCollectionNewMca.equals(ensayos)) {
                        oldIdEnsayoOfMcaCollectionNewMca.getMcaCollection().remove(mcaCollectionNewMca);
                        oldIdEnsayoOfMcaCollectionNewMca = em.merge(oldIdEnsayoOfMcaCollectionNewMca);
                    }
                }
            }
            for (Stepvoltage stepvoltageCollectionOldStepvoltage : stepvoltageCollectionOld) {
                if (!stepvoltageCollectionNew.contains(stepvoltageCollectionOldStepvoltage)) {
                    stepvoltageCollectionOldStepvoltage.setIdEnsayo(null);
                    stepvoltageCollectionOldStepvoltage = em.merge(stepvoltageCollectionOldStepvoltage);
                }
            }
            for (Stepvoltage stepvoltageCollectionNewStepvoltage : stepvoltageCollectionNew) {
                if (!stepvoltageCollectionOld.contains(stepvoltageCollectionNewStepvoltage)) {
                    Ensayos oldIdEnsayoOfStepvoltageCollectionNewStepvoltage = stepvoltageCollectionNewStepvoltage.getIdEnsayo();
                    stepvoltageCollectionNewStepvoltage.setIdEnsayo(ensayos);
                    stepvoltageCollectionNewStepvoltage = em.merge(stepvoltageCollectionNewStepvoltage);
                    if (oldIdEnsayoOfStepvoltageCollectionNewStepvoltage != null && !oldIdEnsayoOfStepvoltageCollectionNewStepvoltage.equals(ensayos)) {
                        oldIdEnsayoOfStepvoltageCollectionNewStepvoltage.getStepvoltageCollection().remove(stepvoltageCollectionNewStepvoltage);
                        oldIdEnsayoOfStepvoltageCollectionNewStepvoltage = em.merge(oldIdEnsayoOfStepvoltageCollectionNewStepvoltage);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ensayos.getIdensayos();
                if (findEnsayos(id) == null) {
                    throw new NonexistentEntityException("The ensayos with id " + id + " no longer exists.");
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
            Ensayos ensayos;
            try {
                ensayos = em.getReference(Ensayos.class, id);
                ensayos.getIdensayos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ensayos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Eca> ecaCollectionOrphanCheck = ensayos.getEcaCollection();
            for (Eca ecaCollectionOrphanCheckEca : ecaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ensayos (" + ensayos + ") cannot be destroyed since the Eca " + ecaCollectionOrphanCheckEca + " in its ecaCollection field has a non-nullable idensayo field.");
            }
            Collection<Mca> mcaCollectionOrphanCheck = ensayos.getMcaCollection();
            for (Mca mcaCollectionOrphanCheckMca : mcaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ensayos (" + ensayos + ") cannot be destroyed since the Mca " + mcaCollectionOrphanCheckMca + " in its mcaCollection field has a non-nullable idEnsayo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes cliente = ensayos.getCliente();
            if (cliente != null) {
                cliente.getEnsayosCollection().remove(ensayos);
                cliente = em.merge(cliente);
            }
            Tecnicos ejecutadoPor = ensayos.getEjecutadoPor();
            if (ejecutadoPor != null) {
                ejecutadoPor.getEnsayosCollection().remove(ensayos);
                ejecutadoPor = em.merge(ejecutadoPor);
            }
            Collection<Stepvoltage> stepvoltageCollection = ensayos.getStepvoltageCollection();
            for (Stepvoltage stepvoltageCollectionStepvoltage : stepvoltageCollection) {
                stepvoltageCollectionStepvoltage.setIdEnsayo(null);
                stepvoltageCollectionStepvoltage = em.merge(stepvoltageCollectionStepvoltage);
            }
            em.remove(ensayos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ensayos> findEnsayosEntities() {
        return findEnsayosEntities(true, -1, -1);
    }

    public List<Ensayos> findEnsayosEntities(int maxResults, int firstResult) {
        return findEnsayosEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Ensayos> findEnsayosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ensayos.class));
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

    public Ensayos findEnsayos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ensayos.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getEnsayosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ensayos> rt = cq.from(Ensayos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
