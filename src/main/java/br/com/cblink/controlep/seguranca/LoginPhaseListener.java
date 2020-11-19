/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.seguranca;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leandro Laurindo
 */
public class LoginPhaseListener implements PhaseListener {

    private FacesContext facesContext;

    @Override
    public void afterPhase(PhaseEvent event) {
        facesContext = event.getFacesContext();
        String viewId = facesContext.getViewRoot().getViewId();

        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        boolean paginaLogin = viewId.lastIndexOf("index") > -1;

        if (existeUsuarioLogado() && paginaLogin) {
            nh.handleNavigation(facesContext, null, "/paginas/home.xhtml?faces-redirect=true");
        } else if (!existeUsuarioLogado() && !paginaLogin) {
            nh.handleNavigation(facesContext, null, "/index?faces-redirect=true");
        }
    }

    public boolean existeUsuarioLogado() {
        return (boolean) getAtributoSessao("logado");
    }

    public Object getAtributoSessao(String attributeName) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            return session.getAttribute(attributeName);
        }
        return false;
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
