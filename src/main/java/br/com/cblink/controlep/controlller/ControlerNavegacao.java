/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Leandro Laurindo
 */
@Named(value = "ControlerNavegacao")
@ViewScoped
public class ControlerNavegacao implements Serializable {

    public ControlerNavegacao() {

    }

    public String importacao() {
        return "/paginas/importacaocsv.xhtml?faces-redirect=true";
    }
    
    public String importacaoClientes() {
        return "/paginas/importaPlanilhaClientes.xhtml?faces-redirect=true";
    }
    public String cadastroUsuario() {
        return "/paginas/controleUsuarios.xhtml?faces-redirect=true";
    }

    public String controleChmados() {
         System.err.println("Chamou controleChmados");
        return "/paginas/controleDeChamados.xhtml?faces-redirect=true";
    }

    public String clientes() {
        return "/paginas/controleClientes.xhtml?faces-redirect=true";
    }
    
    public String fornecedores() {
        return "/paginas/fornecedores.xhtml?faces-redirect=true";
    }
     public String equipamentos() {
        return "/paginas/equipamentos.xhtml?faces-redirect=true";
    }

    public String home() {
        return "/paginas/home.xhtml?faces-redirect=true";
    }

    public String gerar() {
        return "/paginas/gerarArquivo.xhtml?faces-redirect=true";
    }

    public String importar() {
        return "/paginas/importaplanilha.xhtml?faces-redirect=true";
    }
    
    public String importarDidsFsm() {
        return "/paginas/importaplanilhaDidsFsm.xhtml?faces-redirect=true";
    }
    public String importarTelefones() {
        return "/paginas/importaPlanilhaTelefones.xhtml?faces-redirect=true";
    }
    
}
