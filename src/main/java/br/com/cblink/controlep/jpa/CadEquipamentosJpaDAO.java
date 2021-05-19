package br.com.cblink.controlep.jpa;

import br.com.cblink.controlep.entidades.CadEquipamentos;
import br.com.cblink.controlep.entidades.CadEquipamentosDetalhe;
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
public class CadEquipamentosJpaDAO implements Serializable {

    public EntityManager getEntityManager() {
        return ConexaoBD.getConnection();
    }

    public boolean create(CadEquipamentos equipamentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(equipamentos);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CadEquipamentosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public boolean createEquipDetalhe(CadEquipamentosDetalhe equipamentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(equipamentos);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CadEquipamentosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(CadEquipamentos equipamentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.find(CadEquipamentos.class, equipamentos.getIdEquipamentos());
            em.getTransaction().begin();
            em.merge(equipamentos);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CadEquipamentosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public boolean editEquipDetalhe(CadEquipamentosDetalhe equipamentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.find(CadEquipamentosDetalhe.class, equipamentos.getIdEquipDet());
            em.getTransaction().begin();
            em.merge(equipamentos);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CadEquipamentosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            CadEquipamentos equipamentos;
            try {
                equipamentos = em.find(CadEquipamentos.class, id);
                equipamentos.getIdFornecedor();
                em.remove(equipamentos);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(CadEquipamentosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public boolean destroyDetalhes(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CadEquipamentosDetalhe equipamentos;
            try {
                equipamentos = em.find(CadEquipamentosDetalhe.class, id);
                //equipamentos.getIdFornecedor();
                em.remove(equipamentos);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(CadEquipamentosJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<CadEquipamentos> listarCadEquipamentoses(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }

    public List<CadEquipamentos> findCadEquipamentosEntities() {
        return findCadEquipamentosEntities(true, -1, -1);
    }

    public List<CadEquipamentos> findCadEquipamentosEntities(int maxResults, int firstResult) {
        return findCadEquipamentosEntities(false, maxResults, firstResult);
    }

    public List<CadEquipamentos> findCadEquipamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CadEquipamentos.class));
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

    public List<CadEquipamentos> listarConfiltros(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<CadEquipamentosDetalhe> listarConfiltrosDetalhe(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<CadFornecedor> listarFornecedores(String jpql) {
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

    public CadEquipamentos findCadEquipamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CadEquipamentos.class, id);
        } finally {
            em.close();
        }
    }

    public CadEquipamentos carregarCadEquipamentos(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql, CadEquipamentos.class).getSingleResult();
        } catch (Throwable ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    

    public int getCadEquipamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CadEquipamentos> rt = cq.from(CadEquipamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
