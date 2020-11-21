package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.Chamadosatendidos;
import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.jpa.ChamadosJpaDAO;
import br.com.cblink.controlep.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Leandro Laurindo
 */
@ManagedBean(name = "controleChamados")
@ViewScoped
public class ControleChamados implements Serializable {

    private Chamadosatendidos chamados;
    private List<Chamadosatendidos> listaChamadosatendidos;
    private ChamadosJpaDAO jpaController = new ChamadosJpaDAO();
    private List<Clientes> listaClientes;
    private Integer idCliente;
    private String[] idsClientes;
    private Clientes cliente;
    private String descricao;

    @PostConstruct
    public void init() {
        cliente = new Clientes();
        chamados = new Chamadosatendidos();
        listaClientes = new ArrayList<>();
        listaClientes = jpaController.findClientesEntities();
        listarD();
    }

    public void listarD() {
        listaChamadosatendidos = new ArrayList<>();
        List<Chamadosatendidos> aux = new ArrayList<>();
        aux = jpaController.listarAtendimentos("SELECT c FROM Chamadosatendidos c WHERE c.dataFim is null ORDER BY c.dataInicio DESC");
        for (Chamadosatendidos ca : aux) {
            boolean salva = true;
            for (Chamadosatendidos cb : listaChamadosatendidos) {
                if (ca.getClientesId().getNome().equalsIgnoreCase(cb.getClientesId().getNome())) {
                    salva = false;
                    break;
                }
            }
            if (salva) {
                listaChamadosatendidos.add(ca);
            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('pausaTable2').clearFilters()");
        Util.updateComponente("formTbChamados");
    }

    public void listar() {
        listaChamadosatendidos = new ArrayList<>();
        listaChamadosatendidos = jpaController.findChamadosatendidosEntities();
        Util.updateComponente("formTbChamados");
    }

    public void listaConfiltros() {
        String jpql = "SELECT c FROM Chamadosatendidos c";
        String hql = "";
        if (idCliente != null) {
            hql = " WHERE (c.clientesId.id=" + idCliente + "";
            if (!hql.isEmpty()) {
                hql += ")";
                jpql += hql;
            }
        }
        jpql += " ORDER BY c.dataInicio DESC";
        //System.err.println(jpql);
        listaChamadosatendidos = new ArrayList<>();
        listaChamadosatendidos = jpaController.listarConfiltros(jpql);
       // System.err.println(listaChamadosatendidos.size());
        if (listaChamadosatendidos.size() > 0) {
            try {
                cliente = listaChamadosatendidos.get(0).getClientesId();
            } catch (Exception e) {

            }
        }

        Util.updateComponente("formTbChamados");
    }

    public void listaConfiltrosP(Integer id) {
        String jpql = "SELECT c FROM Chamadosatendidos c";
        String hql = "";
        if (id != null) {
            hql = " WHERE (c.clientesId.id=" + id + "";
            if (!hql.isEmpty()) {
                hql += ")";
                jpql += hql;
            }
        }
        jpql += " ORDER BY c.dataInicio DESC";
        //System.err.println(jpql);
        listaChamadosatendidos = new ArrayList<>();
        listaChamadosatendidos = jpaController.listarConfiltros(jpql);
        //System.err.println(listaChamadosatendidos.size());
        if (listaChamadosatendidos.size() > 0) {
            try {
                cliente = listaChamadosatendidos.get(0).getClientesId();
            } catch (Exception e) {

            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('pausaTable2').clearFilters()");
        Util.updateComponente("formTbChamados");
    }

    public void salvar() {
        if (chamados.getId() == null) {
            chamados.setDataInicio(new Date());
            chamados.setClientesId(cliente);
            chamados.setDescricaoservico(chamados.getDescricaoservico().toUpperCase());
            if (jpaController.create(chamados)) {
                Util.criarMensagemInfo("Salvo com sucesso!");
                listar();
                chamados = new Chamadosatendidos();
                Util.updateComponente("cadFrmCham");
            } else {
                Util.criarMensagemErro("Erro não foi possivel salvar!");
            }
        } else {
            chamados.setClientesId(cliente);
            chamados.setDescricaoservico(chamados.getDescricaoservico().toUpperCase());
            if (jpaController.edit(chamados)) {
                Util.criarMensagemInfo("Editado com sucesso!");
                listar();
                chamados = new Chamadosatendidos();
                Util.updateComponente("cadFrmCham");
            } else {
                Util.criarMensagemErro("Erro não foi possivel salvar!");
            }
        }

    }

    public void deletar() {

        if (jpaController.destroy(chamados.getId())) {
            listaChamadosatendidos.remove(chamados);
            Util.criarMensagemInfo("Removido com sucesso!");
            Util.updateComponente("formTbChamados");
        } else {
            Util.criarMensagemErro("Erro: não foi possivel remover!");
        }
    }

    public void novoChamadosatendidos() {
        chamados = new Chamadosatendidos();
        //cliente = new Clientes();
        Util.updateComponente("cadFrmCham");
    }

    public void setarChamadosatendidosEditar(Chamadosatendidos usu) {
        chamados = usu;
        cliente = chamados.getClientesId();
        Util.updateComponente("cadFrmCham");
    }

    public void setarChamadosatendidosRemover(Chamadosatendidos usu) {
        chamados = usu;
    }

    public void finalizar(Chamadosatendidos chamadosatendidos) {
        chamadosatendidos.setDataFim(new Date());
        if (jpaController.edit(chamadosatendidos)) {
            Util.criarMensagemInfo("Finalizado com sucesso!");
            listar();
            chamados = new Chamadosatendidos();
            Util.updateComponente("formTbChamados");
        } else {
            Util.criarMensagemErro("Erro não foi possivel salvar!");
        }
    }

    public void setartelefone() {
        chamados.setTelefone(cliente.getTelefone());
        Util.updateComponente("cadFrmCham");
        Util.chamarFuncaoJs("PF('dlgAtendimento').show()");

    }

    public void setarDescricao(String descr) {
        descricao = descr;
        Util.updateComponente("frmDescricao");
    }

    public List<Chamadosatendidos> getListaChamadosatendidos() {
        return listaChamadosatendidos;
    }

    public void setListaChamadosatendidos(List<Chamadosatendidos> listaChamadosatendidos) {
        this.listaChamadosatendidos = listaChamadosatendidos;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Chamadosatendidos getChamados() {
        return chamados;
    }

    public void setChamados(Chamadosatendidos chamados) {
        this.chamados = chamados;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String[] getIdsClientes() {
        return idsClientes;
    }

    public void setIdsClientes(String[] idsClientes) {
        this.idsClientes = idsClientes;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
