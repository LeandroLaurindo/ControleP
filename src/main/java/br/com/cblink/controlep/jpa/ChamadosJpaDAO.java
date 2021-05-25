package br.com.cblink.controlep.jpa;

import br.com.cblink.controlep.entidades.Chamadosatendidos;
import br.com.cblink.controlep.entidades.Clientes;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Leandro Laurindo
 */
public class ChamadosJpaDAO implements Serializable {

    public EntityManager getEntityManager() {
        return ConexaoBD.getConnection();
    }

    public boolean create(Chamadosatendidos chamados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(chamados);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ChamadosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Chamadosatendidos chamados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.find(Chamadosatendidos.class, chamados.getId());
            em.getTransaction().begin();
            em.merge(chamados);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ChamadosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean destroy(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chamadosatendidos chamados;
            try {
                chamados = em.find(Chamadosatendidos.class, id);
                chamados.getId();
                em.remove(chamados);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(ChamadosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
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

    public List<Chamadosatendidos> listarAtendimentos(String jpql) {
        EntityManager em = getEntityManager();
        try {

            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Chamadosatendidos> findChamadosatendidosEntities() {
        return findChamadosatendidosEntities(true, -1, -1);
    }

    public List<Chamadosatendidos> findChamadosatendidosEntities(int maxResults, int firstResult) {
        return findChamadosatendidosEntities(false, maxResults, firstResult);
    }

    public List<Chamadosatendidos> findChamadosatendidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chamadosatendidos.class));
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

    public List<Chamadosatendidos> listarConfiltros(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> listarTelefones(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }

    public Chamadosatendidos findChamadosatendidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chamadosatendidos.class, id);
        } finally {
            em.close();
        }
    }

    public Chamadosatendidos carregarChamadosatendidos(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql, Chamadosatendidos.class).getSingleResult();
        } catch (Throwable ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getChamadosatendidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chamadosatendidos> rt = cq.from(Chamadosatendidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
