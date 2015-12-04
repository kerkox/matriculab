/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.persistence;

import Matricula.logic.Programa;
import Matricula.persistence.exceptions.NonexistentEntityException;
import Matricula.persistence.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author atenea
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(programa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getCodigo()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            programa = em.merge(programa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = programa.getCodigo();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
