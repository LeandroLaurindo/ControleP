/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Leandro Laurindo
 */
public class Util {

    public Util() {
    }

    public static void criarMensagemAviso(String texto) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    /**
     * *
     * cria uma mensagem do facesmessage, do tipo INFO
     *
     * @param texto
     */
    public static void criarMensagemInfo(String texto) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    /**
     *
     * @param texto
     */
    public static void criarMensagemErro(String texto) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    /**
     *
     * @param id
     */
    public static void resetarComponente(String id) {
        org.primefaces.PrimeFaces.current().resetInputs("");
    }

    /**
     *
     * @param id
     */
    public static void updateComponente(String id) {
        org.primefaces.PrimeFaces.current().ajax().update(id);
    }

    /**
     *
     * @param js
     */
    public static void chamarFuncaoJs(String js) {
        org.primefaces.PrimeFaces.current().executeScript(js);
    }

    /**
     *
     * @param senha
     * @return
     */
    public static String hashPassword(String senha) {

        return BCrypt.hashpw(senha, BCrypt.gensalt());

    }

    /**
     *
     * @param senha
     * @param hashedPassword
     * @return
     */
    public static boolean checkPass(String senha, String hashedPassword) {
        return BCrypt.checkpw(senha, hashedPassword);
    }
    
   /* public static void main(String[] args) {
        Util util = new Util();
        System.err.println(hashPassword("senha1515"));
    }*/
}
