/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.seguranca;

import java.io.IOException;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author Leandro Laurindo
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public JsfExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable exception = context.getException();

            boolean handled = false;

            try {
                if (exception instanceof ViewExpiredException) {
                    handled = true;
                    retornar();
                } else {
                    
                    handled = true;
                    
                }
            } finally {
                if (handled) {
                    events.remove();
                }
            }
        }

        getWrapped().handle();
    }
    public String retornar(){
        return "/faces/index.xhtml?faces-redirect=true";
    }

    private void redirect(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String contextPath = externalContext.getRequestContextPath();

            externalContext.redirect(contextPath);
            facesContext.responseComplete();
        } catch (IOException e) {
            throw new FacesException(e);
        }
    }
}
