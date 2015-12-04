/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matricula.persistence;

import Matricula.logic.Tabulado;
import Matricula.persistence.exceptions.NonexistentEntityException;
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
public class TabuladoJpaController implements Serializable {

    public TabuladoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabulado tabulado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tabulado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabulado tabulado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tabulado = em.merge(tabulado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tabulado.getId();
                if (findTabulado(id) == null) {
                    throw new NonexistentEntityException("The tabulado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabulado tabulado;
            try {
                tabulado = em.getReference(Tabulado.class, id);
                tabulado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabulado with id " + id + " no longer exists.", enfe);
            }
            em.remove(tabulado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabulado> findTabuladoEntities() {
        return findTabuladoEntities(true, -1, -1);
    }

    public List<Tabulado> findTabuladoEntities(int maxResults, int firstResult) {
        return findTabuladoEntities(false, maxResults, firstResult);
    }

    private List<Tabulado> findTabuladoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tabulado.class));
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

    public Tabulado findTabulado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabulado.class, id);
        } finally {
            em.close();
        }
    }

    public Tabulado findTabuladoActual() {
        EntityManager em = getEntityManager();
        return (Tabulado) em.createNamedQuery("Tabulado.findByActual")
                .setParameter("actual", true)
                .getSingleResult();
    }

    public int getTabuladoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tabulado> rt = cq.from(Tabulado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
