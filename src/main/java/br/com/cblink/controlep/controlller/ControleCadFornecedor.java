/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.CadFornecedor;
import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.jpa.CadFornecedorJpaDAO;
import br.com.cblink.controlep.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Felipe Rios
 */
@Named(value = "controleCadFornecedor")
@ViewScoped
public class ControleCadFornecedor implements Serializable {

    private CadFornecedor cadFornecedor;

    private CadFornecedorJpaDAO jpaDAO;

    private List<CadFornecedor> listaFornecedores;

    private List<Clientes> listaClientes;

    private List<CadFornecedor> listaFornecedoresFiltro;

    private List<CadFornecedor> listaDetalhes;

    @PostConstruct
    public void init() {
        this.cadFornecedor = new CadFornecedor();
        this.listaClientes = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();
        this.jpaDAO = new CadFornecedorJpaDAO();
        this.listaClientes = this.jpaDAO.findClientesEntities();
        listar();
    }

    public void listar() {
        this.listaFornecedores = this.jpaDAO.findCadFornecedorEntities();
        Util.updateComponente("formtableForne");
    }

    public void listarComfiltros(String filtros) {
        String hql = "SELECT c FROM CadFornecedor c " + filtros;
        this.listaFornecedores = this.jpaDAO.listarConfiltros(hql);
        Util.updateComponente("formtableForne");
    }

    public void salvar() {
        if (this.cadFornecedor.getIdFornecedor() == null) {
            if (this.jpaDAO.create(this.cadFornecedor)) {
                listar();
                Util.criarMensagemInfo("Cadastro realizado com sucesso!");
                Util.chamarFuncaoJs("PF('dlgCadForne').hide();");
            } else {
                Util.criarMensagemErro("Erro ao cadastro!");
            }
        } else {
            if (this.jpaDAO.edit(this.cadFornecedor)) {
                listar();
                Util.criarMensagemInfo("Alteração realizada com sucesso!");
                Util.chamarFuncaoJs("PF('dlgCadForne').hide();");
            } else {
                Util.criarMensagemErro("Erro ao editar!");
            }
        }
    }

    public void deletar(CadFornecedor fornecedor) {
        if (fornecedor.getIdFornecedor() != null) {
            if (this.jpaDAO.destroy(fornecedor.getIdFornecedor())) {
                this.listaFornecedores.remove(fornecedor);
                Util.criarMensagemInfo("Exclusão realizada com sucesso!");
                 Util.updateComponente("formtableForne");
            } else {
                Util.criarMensagemErro("Erro ao excluir!");
            }
        } else {
            Util.criarMensagemAviso("Fornecedor não pode ser removido esta com id nulo!");
        }
    }

    public void setar(CadFornecedor cf) {
        this.cadFornecedor = new CadFornecedor();
        this.cadFornecedor = cf;
    }

    public void setarDetalhes(CadFornecedor cf) {
        this.listaDetalhes = new ArrayList<>();
        this.listaDetalhes.add(cf);
    }

    public void novo() {
        this.cadFornecedor = new CadFornecedor();
    }

    public CadFornecedor getCadFornecedor() {
        return cadFornecedor;
    }

    public void setCadFornecedor(CadFornecedor cadFornecedor) {
        this.cadFornecedor = cadFornecedor;
    }

    public List<CadFornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<CadFornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<CadFornecedor> getListaFornecedoresFiltro() {
        return listaFornecedoresFiltro;
    }

    public void setListaFornecedoresFiltro(List<CadFornecedor> listaFornecedoresFiltro) {
        this.listaFornecedoresFiltro = listaFornecedoresFiltro;
    }

    public List<CadFornecedor> getListaDetalhes() {
        return listaDetalhes;
    }

    public void setListaDetalhes(List<CadFornecedor> listaDetalhes) {
        this.listaDetalhes = listaDetalhes;
    }

}
