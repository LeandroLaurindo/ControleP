/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep;

import br.com.cblink.controlep.entidades.Usuario;
import br.com.cblink.controlep.jpa.UsuarioJpaDAO;
import br.com.cblink.controlep.util.Util;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leandro Laurindo
 */
@Named(value = "controleLogin")
@SessionScoped
public class Login implements Serializable {

    private UsuarioJpaDAO jpaController = new UsuarioJpaDAO();

    private String login;

    private String senha;

    private Integer nivel;

    private Boolean usuarioLogado;
    
    private Date dataH;

    @PostConstruct
    public void init() {

    }

    public String logar() {
        String jpql = "SELECT u FROM Usuario u WHERE u.login='"+login.toLowerCase().trim()+"' AND u.senha='"+senha.toLowerCase().trim()+"'";
        Usuario usuario = jpaController.carregarUsuario(jpql);
        if (usuario == null) {
            Util.criarMensagemErro("Usuário não encontrado!");
            Util.criarMensagemErro("Erro no Login!");
            return null;
        } else {

            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("logado", true);
            return "/paginas/home.xhtml?faces-redirect=true";
        }
    }

    public void deslogar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        HttpSession session = (HttpSession) ec.getSession(false);
        session.removeAttribute("Usuariogado");
        session.removeAttribute("nivel");
        session.invalidate();
        ec.invalidateSession();
        login = "";
        senha = "";
        usuarioLogado = false;
        nivel = 0;
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String contextPath = externalContext.getRequestContextPath();
            externalContext.redirect(contextPath);
            facesContext.responseComplete();
        } catch (Throwable ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

   

    public Boolean getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Boolean usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Date getDataH() {
        return dataH;
    }

    public void setDataH(Date dataH) {
        this.dataH = dataH;
    }
  
}
