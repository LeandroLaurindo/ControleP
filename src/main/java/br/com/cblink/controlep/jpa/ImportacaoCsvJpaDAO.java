package br.com.cblink.controlep.jpa;

import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.entidades.Dids;
import br.com.cblink.controlep.entidades.Didsfsm;
import br.com.cblink.controlep.entidades.Telefones;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Leandro Laurindo
 */
public class ImportacaoCsvJpaDAO implements Serializable {

    public EntityManager getEntityManager() {
        return ConexaoBD.getConnection();
    }

    public boolean create(Dids dids) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dids);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String verifcar(String numero) {
        String hql = "SELECT c.numero FROM Dids c WHERE c.numero = '" + numero + "'";
        EntityManager em = getEntityManager();
        try {
            return (String) em.createQuery(hql).getSingleResult();
        } catch (NoResultException ex) {
            return "";
        }
    }

    public boolean createMultiplus(List<Dids> lista) {
        EntityManager em = null;
        try {
            for (Dids dids : lista) {
                em = getEntityManager();
                em.getTransaction().begin();
                em.persist(dids);
                em.getTransaction().commit();
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean createDidsFsm(Didsfsm dids) {
        EntityManager em = getEntityManager();
        try {
            Integer id = carregarDisDidsfsm(dids.getNumero());
            if (id == null) {
                em.getTransaction().begin();
                em.persist(dids);
                em.getTransaction().commit();
            } else {
                em.find(Didsfsm.class, id);
                em.getTransaction().begin();
                em.merge(dids);
                em.getTransaction().commit();
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    
     public boolean createClientes(Clientes clientes) {
        EntityManager em = getEntityManager();
        try {
            Integer id = carregarClientes(clientes.getCpfCnpj());
            if (id == null) {
                em.getTransaction().begin();
                em.persist(clientes);
                em.getTransaction().commit();
            } else {
                em.find(Clientes.class, id);
                em.getTransaction().begin();
                em.merge(clientes);
                em.getTransaction().commit();
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

      public boolean createTelefones(Telefones telefones) {
        EntityManager em = getEntityManager();
        try {
            Integer id = carregarTelefones(telefones.getNumero());
            if (id == null) {
                em.getTransaction().begin();
                em.persist(telefones);
                em.getTransaction().commit();
            } else {
                em.find(Telefones.class, id);
                em.getTransaction().begin();
                em.merge(telefones);
                em.getTransaction().commit();
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public boolean createMultiplusDidsFsm(List<Didsfsm> lista) {
        EntityManager em = getEntityManager();
        try {

            for (Didsfsm dids : lista) {
                Integer id = carregarDisDidsfsm(dids.getNumero());
                if (id == null) {
                    em.getTransaction().begin();
                    em.persist(dids);
                    em.getTransaction().commit();
                } else {
                    em.find(Didsfsm.class, id);
                    em.getTransaction().begin();
                    em.merge(dids);
                    em.getTransaction().commit();
                }
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean createMultiplusClientes(List<Clientes> lista) {
        EntityManager em = null;
        try {
            for (Clientes clientes : lista) {
                em = getEntityManager();
                em.getTransaction().begin();
                em.persist(clientes);
                em.getTransaction().commit();
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Dids dids) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.find(Dids.class, dids.getId());
            em.getTransaction().begin();
            em.merge(dids);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Dids dids;
            try {
                dids = em.find(Dids.class, id);
                dids.getId();
                em.remove(dids);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(ImportacaoCsvJpaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dids> findDidsEntities() {
        return findDidsEntities(true, -1, -1);
    }

    public List<Dids> findDidsEntities(int maxResults, int firstResult) {
        return findDidsEntities(false, maxResults, firstResult);
    }

    public List<Dids> findDidsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dids.class));
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

    public Dids findDids(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dids.class, id);
        } finally {
            em.close();
        }
    }

    public Integer carregarDisDidsfsm(String telefone) {
        EntityManager em = getEntityManager();
        try {
            return (Integer) em.createQuery("SELECT c.id FROM Didsfsm c WHERE c.numero= '" + telefone + "'").getSingleResult();
        } catch (Throwable e) {
            return null;
        } finally {
            em.close();
        }
    }
    
     public Integer carregarClientes(String documento) {
        EntityManager em = getEntityManager();
        try {
            return (Integer) em.createQuery("SELECT c.id FROM Clientes c WHERE c.cpfCnpj= '" + documento + "'").getSingleResult();
        } catch (Throwable e) {
            return null;
        } finally {
            em.close();
        }
    }
 public Integer carregarTelefones(String telefone) {
        EntityManager em = getEntityManager();
        try {
            return (Integer) em.createQuery("SELECT c.idTelefone FROM Telefones c WHERE c.numero= '" + telefone + "'").getSingleResult();
        } catch (Throwable e) {
            return null;
        } finally {
            em.close();
        }
    }
     
    public Dids carregarDids(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql, Dids.class).getSingleResult();
        } catch (Throwable ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getDidsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dids> rt = cq.from(Dids.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<String> busccarNomesColumas(String tab) {

        EntityManager manager = ConexaoBD.getConnection();
        try {
            return manager.createNativeQuery("SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '" + tab + "'").getResultList();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        manager.close();
        return null;
    }

}
