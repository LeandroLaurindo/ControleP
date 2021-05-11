/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.CadEquipamentos;
import br.com.cblink.controlep.entidades.CadFornecedor;
import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.jpa.CadEquipamentosJpaDAO;
import br.com.cblink.controlep.jpa.CadFornecedorJpaDAO;
import br.com.cblink.controlep.jpa.ClienteJpaDAO;
import br.com.cblink.controlep.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Felipe Rios
 */
@Named(value = "controleCadEquipamentos")
@ViewScoped
public class ControleCadEquipamentos implements Serializable {

    private CadFornecedor cadFornecedor;

    private CadFornecedorJpaDAO jpaDAO;

    private CadEquipamentosJpaDAO cejdao;

    private ClienteJpaDAO cjdao;

    private List<CadEquipamentos> listaEquipamentos;

    private CadEquipamentos cadEquipamentos;

    private List<Clientes> listaClientes;

    private Clientes clientes;

    private List<CadFornecedor> listaFornecedores;

    @PostConstruct
    public void init() {
        this.cadFornecedor = new CadFornecedor();
        this.listaClientes = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();
        this.jpaDAO = new CadFornecedorJpaDAO();
        this.cejdao = new CadEquipamentosJpaDAO();
        this.cjdao = new ClienteJpaDAO();
        this.listaEquipamentos = new ArrayList<>();
        this.cadEquipamentos = new CadEquipamentos();
        this.listaClientes = new ArrayList<>();
        this.clientes = new Clientes();
        listar();
        listarFornecedores();
        listarClientes();
    }

    public void listarFornecedores() {
        this.listaFornecedores = new ArrayList<>();
        this.listaFornecedores = this.jpaDAO.findCadFornecedorEntities();
        Util.updateComponente("");
    }

    public void listarFornecedorComfiltros(String filtros) {
        this.listaFornecedores = new ArrayList<>();
        String hql = "SELECT c FROM CadFornecedor c " + filtros;
        this.listaFornecedores = this.jpaDAO.listarConfiltros(hql);
    }

    public void listar() {
        this.listaEquipamentos = new ArrayList<>();
        this.listaEquipamentos = this.cejdao.findCadEquipamentosEntities();
        Util.updateComponente("frmTableEquimentos");
    }

    public void listarComFiltros(String filtros) {
        this.listaEquipamentos = new ArrayList<>();
        String hql = "SELECT c FROM CadEquipamentos c " + filtros;
        this.listaEquipamentos = this.cejdao.listarConfiltros(hql);
        Util.updateComponente("frmTableEquimentos");
    }

    public void listarClientes() {
        this.listaClientes = new ArrayList<>();
        this.listaClientes = this.cjdao.findClientesEntities();
    }

    public void listarClientesComFiltros(String filtros) {
        this.listaClientes = new ArrayList<>();
        String hql = "SELECT c FROM Cleintes c " + filtros;
        this.listaClientes = this.cjdao.listarClientesConfiltros(hql);
    }

    public void salvar() {
        if (this.cadEquipamentos.getIdEquipamentos() == null) {
            this.cadEquipamentos.setDataEquipamento(Calendar.getInstance().getTime());
            if (this.cejdao.create(this.cadEquipamentos)) {
                listar();
                this.cadEquipamentos = new CadEquipamentos();
                Util.criarMensagemInfo("Cadastro realizado com sucesso!");
                Util.chamarFuncaoJs("PF('dlgCadEquipa').hide();");
            } else {
                Util.criarMensagemErro("Erro ao cadastro!");
            }
        } else {
             this.cadEquipamentos.setDataEquipamento(Calendar.getInstance().getTime());
            if (this.cejdao.edit(this.cadEquipamentos)) {
                listar();
                this.cadEquipamentos = new CadEquipamentos();
                Util.criarMensagemInfo("Alteração realizada com sucesso!");
                Util.chamarFuncaoJs("PF('dlgCadEquipa').hide();");
            } else {
                Util.criarMensagemErro("Erro ao editar!");
            }
        }
    }

    public void deletar() {
        if (this.cadEquipamentos.getIdEquipamentos() != null) {
            if (this.cejdao.destroy(this.cadEquipamentos.getIdEquipamentos())) {
                Util.criarMensagemInfo("Exclusão realizada com sucesso!");
            } else {
                Util.criarMensagemErro("Erro ao excluir!");
            }
        } else {
            Util.criarMensagemAviso("Equipamento não pode ser removido esta com id nulo!");
        }
    }

    public void deletar(CadEquipamentos ce) {
        if (ce.getIdEquipamentos() != null) {
            if (this.cejdao.destroy(ce.getIdEquipamentos())) {
                this.listaEquipamentos.remove(ce);
                Util.criarMensagemInfo("Exclusão realizada com sucesso!");
                Util.updateComponente("frmTableEquimentos");
            } else {
                Util.criarMensagemErro("Erro ao excluir!");
            }
        } else {
            Util.criarMensagemAviso("Equipamento não pode ser removido esta com id nulo!");
        }
    }

    public void setar(CadEquipamentos cf) {
        this.cadEquipamentos = new CadEquipamentos();
        this.cadEquipamentos = cf;
    }

    public void novo() {
        this.cadEquipamentos = new CadEquipamentos();
        this.cadEquipamentos.setCodCliente(0);
    }

    public void mostrarFabricante(CadEquipamentos cf) {
        if (cf.getIdFornecedor() == null) {
            Util.criarMensagemAviso("Nenhum fabricante encontrado!");
        } else {
            this.cadEquipamentos = new CadEquipamentos();
            this.cadEquipamentos = cf;
            Util.updateComponente("frmMfabricante");
            Util.chamarFuncaoJs("PF('dlgMfabricante').show();");
        }
    }

    public void mostrarFornecedor(CadEquipamentos cf) {
        if (cf.getIdFornecedor() == null) {
            Util.criarMensagemAviso("Nenhum fornecedor encontrado!");
        } else {
            this.cadFornecedor = new CadFornecedor();
            this.cadFornecedor = this.jpaDAO.findCadFornecedor(cf.getIdFornecedor());
            Util.updateComponente("frmMFonecedor");
            Util.chamarFuncaoJs("PF('dlgMFonecedor').show();");
        }
    }

    public void mostrarCliente(CadEquipamentos cf) {
        try {
         Integer id = cf.getCodCliente();
            this.clientes = new Clientes();
            this.clientes = this.cjdao.findClientes(id);
            Util.updateComponente("frmMcliente");
            Util.chamarFuncaoJs("PF('dlgMcliente').show();");
        } catch (Exception ex) {
            Logger.getLogger(ControleCadEquipamentos.class.getName()).log(Level.SEVERE, null, ex);
            Util.criarMensagemAviso("Nenhum cliente encontrado!");
        }
    }

    public void mostrarObs(CadEquipamentos cf) {
        if (cf.getIdFornecedor() == null) {
            Util.criarMensagemAviso("Nenhum observação encontrada!");
        } else {
            this.cadEquipamentos = new CadEquipamentos();
            this.cadEquipamentos = cf;
            Util.updateComponente("frmObsx");
            Util.chamarFuncaoJs("PF('dlgObsx').show();");
        }
    }
    
     public void editarObs(CadEquipamentos cf) {
        if (cf.getIdFornecedor() == null) {
            Util.criarMensagemAviso("Nenhum observação encontrada!");
        } else {
            this.cadEquipamentos = new CadEquipamentos();
            this.cadEquipamentos = cf;
            Util.updateComponente("frmObsxEditar");
            Util.chamarFuncaoJs("PF('dlgObsxEditar').show();");
        }
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

    public List<CadEquipamentos> getListaEquipamentos() {
        return listaEquipamentos;
    }

    public void setListaEquipamentos(List<CadEquipamentos> listaEquipamentos) {
        this.listaEquipamentos = listaEquipamentos;
    }

    public CadEquipamentos getCadEquipamentos() {
        return cadEquipamentos;
    }

    public void setCadEquipamentos(CadEquipamentos cadEquipamentos) {
        this.cadEquipamentos = cadEquipamentos;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

}
