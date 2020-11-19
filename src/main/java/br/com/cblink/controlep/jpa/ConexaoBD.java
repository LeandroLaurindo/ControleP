/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.jpa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Filipe
 */
public class ConexaoBD {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getConnection() {
        try {
            emf = Persistence.createEntityManagerFactory("controlePU");
            return emf.createEntityManager();
        } catch (Exception e) {
            System.out.println("CONEXAOBD" + e);
            return emf.createEntityManager();
        } finally {
        }
    }

    public static EntityManagerFactory getConnectionFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlePU");
        try {
            return emf;
        } catch (Exception e) {
            System.out.println("CONEXAOBD" + e);
            return emf;
        } finally {
        }
    }
}
