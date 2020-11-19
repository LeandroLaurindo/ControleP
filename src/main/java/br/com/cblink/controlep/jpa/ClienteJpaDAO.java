package br.com.cblink.controlep.jpa;

import br.com.cblink.controlep.entidades.Chamadosatendidos;
import br.com.cblink.controlep.entidades.Clientes;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ClienteJpaDAO implements Serializable {

    public EntityManager getEntityManager() {
        return ConexaoBD.getConnection();
    }

    public boolean create(Clientes cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Clientes cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.find(Clientes.class, cliente.getId());
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Clientes cliente;
            try {
                cliente = em.find(Clientes.class, id);
                cliente.getId();
                em.remove(cliente);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(ClienteJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean destroyDados(Integer id) {
        EntityManager manager = null;
        List<Chamadosatendidos> lista = new ArrayList<>();
        try {
            manager = getEntityManager();
            lista = manager.createQuery("SELECT c FROM Chamadosatendidos c WHERE c.clientesId.id =" + id + "").getResultList();
        } catch (Exception e) {

        } finally {
            if (manager != null) {
                manager.close();
            }
        }

        EntityManager em = getEntityManager();
        boolean retorno = false;
        for (Chamadosatendidos cht : lista) {

            em.getTransaction().begin();
            try {
                Chamadosatendidos c = em.find(Chamadosatendidos.class, cht.getId());
                c.getId();
                em.remove(c);
                em.getTransaction().commit();
                retorno = true;
            } catch (Exception ex) {
                Logger.getLogger(ClienteJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
                retorno = false;
            }
        }
        if (em != null) {
            em.close();
        }
        return retorno;
    }

    public List<Clientes> findClientesEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Clientes c ORDER BY c.id DESC").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public Clientes carregarClientes(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql, Clientes.class).getSingleResult();
        } catch (Throwable ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
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
