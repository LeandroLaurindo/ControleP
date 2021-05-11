package br.com.cblink.controlep.jpa;

import br.com.cblink.controlep.entidades.CadFornecedor;
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
public class CadFornecedorJpaDAO implements Serializable {

    public EntityManager getEntityManager() {
        return ConexaoBD.getConnection();
    }

    public boolean create(CadFornecedor fornecedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CadFornecedorJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(CadFornecedor fornecedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.find(CadFornecedor.class, fornecedor.getIdFornecedor());
            em.getTransaction().begin();
            em.merge(fornecedor);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CadFornecedorJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            CadFornecedor fornecedor;
            try {
                fornecedor = em.find(CadFornecedor.class, id);
                fornecedor.getIdFornecedor();
                em.remove(fornecedor);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(CadFornecedorJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<CadFornecedor> listarCadFornecedores(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }

    public List<CadFornecedor> findCadFornecedorEntities() {
        return findCadFornecedorEntities(true, -1, -1);
    }

    public List<CadFornecedor> findCadFornecedorEntities(int maxResults, int firstResult) {
        return findCadFornecedorEntities(false, maxResults, firstResult);
    }

    public List<CadFornecedor> findCadFornecedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CadFornecedor.class));
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

    public List<CadFornecedor> listarConfiltros(String jpql) {
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

    public CadFornecedor findCadFornecedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CadFornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public CadFornecedor carregarCadFornecedor(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql, CadFornecedor.class).getSingleResult();
        } catch (Throwable ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getCadFornecedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CadFornecedor> rt = cq.from(CadFornecedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
