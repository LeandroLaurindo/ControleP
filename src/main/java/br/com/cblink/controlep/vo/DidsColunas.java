/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.vo;

import java.io.Serializable;

/**
 *
 * @author Leandro Laurindo
 */
public class DidsColunas implements Serializable{
    
    private Integer indice;
    
    private String coluna;

    public DidsColunas() {
    }

    public DidsColunas(Integer indice, String coluna) {
        this.indice = indice;
        this.coluna = coluna;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }
    
    
    
}
