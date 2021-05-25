/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.jpa;

import br.com.cblink.controlep.entidades.Dids;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro Laurindo
 */
public class GerarArquivoDAO implements Serializable {

    public GerarArquivoDAO() {
    }

    public EntityManager getEntityManager() {
        return ConexaoBD.getConnection();
    }

    public List<Dids> listarRegistros(String jpql) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(jpql).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
