/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.xml;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ThiagoRabelo (61) 9 9935-1310
 */
@ManagedBean(name = "col")
public class Col implements Serializable {

    private static final long serialVersionUID = 1L;
    private String coluna;
    private String campoAssociado;
    private int indice;

    /**
     * @return the coluna
     */
    public String getColuna() {
        return coluna;
    }

    /**
     * @param coluna the coluna to set
     */
    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    /**
     * @return the campoAssociado
     */
    public String getCampoAssociado() {
        return campoAssociado;
    }

    /**
     * @param campoAssociado the campoAssociado to set
     */
    public void setCampoAssociado(String campoAssociado) {
        this.campoAssociado = campoAssociado;
    }

    /**
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }
}