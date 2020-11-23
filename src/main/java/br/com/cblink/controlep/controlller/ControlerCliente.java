/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.jpa.ClienteJpaDAO;
import br.com.cblink.controlep.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 *
 * @author Leandro Laurindo
 */
@Named(value ="controlerCliente")
@ViewScoped
public class ControlerCliente implements Serializable {

    private Clientes clientes;

    private List<Clientes> listaDeClientes;
    
     private List<Clientes> listaDeClientesfiltro;

    private ClienteJpaDAO jpaController = new ClienteJpaDAO();

    @PostConstruct
    public void init() {
        clientes = new Clientes();
        listaDeClientesfiltro = new ArrayList<>();
        listar();
    }

    public void listar() {
        listaDeClientes = new ArrayList<>();
        listaDeClientes = jpaController.findClientesEntities();
        listaDeClientesfiltro = new ArrayList<>();
        listaDeClientesfiltro = listaDeClientes;
        Util.updateComponente("formTbClientes");
    }

    public void salvar() {
        if (clientes.getId() == null) {
            if (jpaController.create(clientes)) {
                Util.criarMensagemInfo("Salvo com sucesso!");
                listar();
                clientes = new Clientes();
                Util.updateComponente("cadCli");
            } else {
                Util.criarMensagemErro("Erro não foi possivel salvar!");
            }
        } else {
            if (jpaController.edit(clientes)) {
                Util.criarMensagemInfo("Editado com sucesso!");
                listar();
                clientes = new Clientes();
                Util.updateComponente("cadCli");
            } else {
                Util.criarMensagemErro("Erro não foi possivel salvar!");
            }
        }

    }

    public void deletar() {
        if (jpaController.destroyDados(clientes.getId())) {
            if (jpaController.destroy(clientes.getId())) {
                listaDeClientes.remove(clientes);
                Util.criarMensagemInfo("Removido com sucesso!");
                Util.updateComponente("formTbClientes");
            } else {
                Util.criarMensagemErro("Erro: não foi possivel remover!");
            }
        } else {
            Util.criarMensagemErro("Erro: não foi possivel remover!");
        }
    }

    public void novoCliente() {
        clientes = new Clientes();
        Util.updateComponente("cadCli");
    }

    public void setarClienteEditar(Clientes usu) {
        clientes = usu;
        Util.updateComponente("cadCli");
    }

    public void setarClienteRemover(Clientes usu) {
        clientes = usu;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public List<Clientes> getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(List<Clientes> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public List<Clientes> getListaDeClientesfiltro() {
        return listaDeClientesfiltro;
    }

    public void setListaDeClientesfiltro(List<Clientes> listaDeClientesfiltro) {
        this.listaDeClientesfiltro = listaDeClientesfiltro;
    }
  
}
