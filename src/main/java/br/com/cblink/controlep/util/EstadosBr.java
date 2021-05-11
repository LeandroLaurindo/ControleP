/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Leandro Laurindo
 */
@Named
@ViewScoped
public class EstadosBr implements Serializable {

    private String nome;
    private String sigla;

    private List<EstadosBr> lista = new ArrayList<>();

    public EstadosBr(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public EstadosBr() {
        montarLista();
    }

    public void montarLista() {
        lista.add(new EstadosBr("Acre", "AC"));
        lista.add(new EstadosBr("Alogoas", "AL"));
        lista.add(new EstadosBr("Amapá", "AM"));
        lista.add(new EstadosBr("Bahia", "BA"));
        lista.add(new EstadosBr("Ceará", "CE"));
        lista.add(new EstadosBr("Distrito Federal", "DF"));
        lista.add(new EstadosBr("Espírito Santo", "ES"));
        lista.add(new EstadosBr("Goiás", "GO"));
        lista.add(new EstadosBr("Maranhão", "MA"));
        lista.add(new EstadosBr("Mato Grosso", "MT"));
        lista.add(new EstadosBr("Mato Grosso do Sul", "MS"));
        lista.add(new EstadosBr("Minas Gerais", "MG"));
        lista.add(new EstadosBr("Pará", "PA"));
        lista.add(new EstadosBr("Paraíba", "PB"));
        lista.add(new EstadosBr("Paraná", "PR"));
        lista.add(new EstadosBr("Pernambuco", "PE"));
        lista.add(new EstadosBr("Piauí", "PI"));
        lista.add(new EstadosBr("Rio de Janeiro", "RJ"));
        lista.add(new EstadosBr("Rio Grande do Norte", "RN"));
        lista.add(new EstadosBr("Rio Grande do Sul", "RS"));
        lista.add(new EstadosBr("Rondônia", "RO"));
        lista.add(new EstadosBr("Roraima", "RR"));
        lista.add(new EstadosBr("Santa Catarina", "SC"));
        lista.add(new EstadosBr("São Paulo", "SP"));
        lista.add(new EstadosBr("Sergipe", "SE"));
        lista.add(new EstadosBr("Tocantins", "TO"));

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<EstadosBr> getLista() {
        return lista;
    }

    public void setLista(List<EstadosBr> lista) {
        this.lista = lista;
    }
    
    
}
