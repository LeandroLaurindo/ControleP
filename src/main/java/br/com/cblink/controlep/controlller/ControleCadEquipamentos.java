/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.CadEquipamentos;
import br.com.cblink.controlep.entidades.CadEquipamentosDetalhe;
import br.com.cblink.controlep.entidades.CadFornecedor;
import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.jpa.CadEquipamentosJpaDAO;
import br.com.cblink.controlep.jpa.CadFornecedorJpaDAO;
import br.com.cblink.controlep.jpa.ClienteJpaDAO;
import br.com.cblink.controlep.util.ArquivoImportacaoBackup;
import br.com.cblink.controlep.util.Util;
import java.io.File;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

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

    private List<CadEquipamentosDetalhe> listaEquipamentosDetalhe;

    private CadEquipamentosDetalhe cadEquipamentosDetalhe;

    private List<Clientes> listaClientes;

    private Clientes clientes;

    private List<CadFornecedor> listaFornecedores;

    private String nomeEquipamentoDetalhado = "";
    private Integer idEquipamentoDetalhado = 0;

    private UploadedFile filess;

    private List<File> arquivos;

    private File fileRemove;

    @PostConstruct
    public void init() {
        this.cadFornecedor = new CadFornecedor();
        this.listaClientes = new ArrayList<>();
        this.listaFornecedores = new ArrayList<>();
        this.jpaDAO = new CadFornecedorJpaDAO();
        this.cejdao = new CadEquipamentosJpaDAO();
        this.cjdao = new ClienteJpaDAO();
        this.listaEquipamentos = new ArrayList<>();
        this.listaEquipamentosDetalhe = new ArrayList<>();
        this.cadEquipamentos = new CadEquipamentos();
        this.listaClientes = new ArrayList<>();
        this.clientes = new Clientes();
        this.cadEquipamentosDetalhe = new CadEquipamentosDetalhe();
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

    public void salvarDetalhes() {
        if (this.cadEquipamentosDetalhe.getIdEquipDet() == null) {
            if (this.cejdao.createEquipDetalhe(this.cadEquipamentosDetalhe)) {
                //listar();
                this.cadEquipamentosDetalhe = new CadEquipamentosDetalhe();
                Util.criarMensagemInfo("Cadastro realizado com sucesso!");
                Util.chamarFuncaoJs("PF('dlgCadEquipDetalhe').hide();");
                //Util.updateComponente("dlgDetalhesEquipamento");
            } else {
                Util.criarMensagemErro("Erro ao cadastro!");
            }
        } else {
            if (this.cejdao.editEquipDetalhe(this.cadEquipamentosDetalhe)) {
                //listar();
                this.cadEquipamentosDetalhe = new CadEquipamentosDetalhe();
                Util.criarMensagemInfo("Alteração realizada com sucesso!");
                Util.chamarFuncaoJs("PF('dlgCadEquipDetalhe').hide();");
                //Util.updateComponente("dlgDetalhesEquipamento");
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

    public void deletarDetalhes(CadEquipamentosDetalhe ce) {
        if (ce.getIdEquipDet() != null) {
            if (this.cejdao.destroyDetalhes(ce.getIdEquipDet())) {
                this.listaEquipamentosDetalhe.remove(ce);
                Util.criarMensagemInfo("Exclusão realizada com sucesso!");
                //Util.updateComponente("dlgDetalhesEquipamento");
            } else {
                Util.criarMensagemErro("Erro ao excluir!");
            }
        } else {
            Util.criarMensagemAviso("Equipamento não pode ser removido esta com id nulo!");
        }
    }

    public void listaConfiltrosEquipDetalhes(Integer id, String nomeEquip) {
        String jpql = "SELECT c FROM CadEquipamentosDetalhe c";
        String hql = "";
        if (id != null) {
            hql = " WHERE (c.idEquipamento=" + id + "";
            if (!hql.isEmpty()) {
                hql += ")";
                jpql += hql;
            }
        }
        //jpql += " ORDER BY c.dataInicio DESC";
        //System.err.println(jpql);
        listaEquipamentosDetalhe = new ArrayList<>();
        listaEquipamentosDetalhe = cejdao.listarConfiltrosDetalhe(jpql);
        setNomeEquipamentoDetalhado(nomeEquip);
        setIdEquipamentoDetalhado(id);
        //System.err.println(listaChamadosatendidos.size());
        /*if (listaEquipamentosDetalhe.size() > 0) {
            try {
                cliente = listaChamadosatendidos.get(0).getClientesId();
            } catch (Exception e) {

            }
        }*/

        //Util.chamarFuncaoJs("PF('dlgTableEquipDetalhe').show();");
        //Util.chamarFuncaoJs("PF('dlgTableEquip').hide();");
        //org.primefaces.PrimeFaces.current().executeScript("PF('pausaTable2').clearFilters()");
        //org.primefaces.PrimeFaces.current().ajax().update("frmTableEquimentosDetalhes");
    }

    public void setar(CadEquipamentos cf) {
        this.cadEquipamentos = new CadEquipamentos();
        this.cadEquipamentos = cf;
    }

    public void setarDetalhes(CadEquipamentosDetalhe cf) {
        this.cadEquipamentosDetalhe = new CadEquipamentosDetalhe();
        this.cadEquipamentosDetalhe = cf;
    }

    public void novo() {
        this.cadEquipamentos = new CadEquipamentos();
        this.cadEquipamentos.setCodCliente(0);
    }

    public void novoDetalhe() {
        this.cadEquipamentosDetalhe = new CadEquipamentosDetalhe();
        this.cadEquipamentosDetalhe.setIdEquipamento(getIdEquipamentoDetalhado());
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

    public void upload() {
        try {
            if (filess != null) {
                String nomebackup = this.cadEquipamentos.getNome() + "_" + filess.getFileName();
                Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String x = formatter.format(new Date());
                nomebackup += x.replaceAll("\\-", "_").replaceAll("\\:", "_").replaceAll(" ", "_").trim();

                ArquivoImportacaoBackup.escrever(nomebackup, filess.getContents());
                Util.criarMensagemAviso("Arquivo gravado com secesso!");
            }
        } catch (Exception ex) {
        }
    }

    public void setarNomeArquivos(CadEquipamentos equipamentos) {
        this.cadEquipamentos = new CadEquipamentos();
        this.cadEquipamentos = equipamentos;
        listarAraquivos();
    }

    public void listarAraquivos() {
        arquivos = new ArrayList<>();
        List<File> aux = new ArrayList<>(ArquivoImportacaoBackup.listar());
        for (File file : aux) {
            try {
                if (file.getName().contains(this.cadEquipamentos.getNome())) {
                    arquivos.add(file);
                }
            } catch (Exception e) {
            }
        }
        Util.updateComponente("formTblArquivo");
        Util.chamarFuncaoJs("PF('dlgTblArquivos').show();");
    }

    public void setarFileRemover(File file) {
        setFileRemove(file);
        Util.updateComponente("formLogConfirma");
        Util.chamarFuncaoJs("PF('dlgRemoverFile').show()");
    }

    public void remover() {
        try {
            fileRemove.delete();
            Util.chamarFuncaoJs("PF('dlgRemoverFile').hide()");
            Util.criarMensagemAviso("Arquivo removido com secesso!");
            listarAraquivos();
        } catch (Throwable ex) {

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

    public CadEquipamentosDetalhe getCadEquipamentosDetalhe() {
        return cadEquipamentosDetalhe;
    }

    public void setCadEquipamentosDetalhe(CadEquipamentosDetalhe cadEquipamentosDetalhe) {
        this.cadEquipamentosDetalhe = cadEquipamentosDetalhe;
    }

    public List<CadEquipamentosDetalhe> getListaEquipamentosDetalhe() {
        return listaEquipamentosDetalhe;
    }

    public void setListaEquipamentosDetalhe(List<CadEquipamentosDetalhe> listaEquipamentosDetalhe) {
        this.listaEquipamentosDetalhe = listaEquipamentosDetalhe;
    }

    public String getNomeEquipamentoDetalhado() {
        return nomeEquipamentoDetalhado;
    }

    public void setNomeEquipamentoDetalhado(String nomeEquipamentoDetalhado) {
        this.nomeEquipamentoDetalhado = nomeEquipamentoDetalhado;
    }

    public Integer getIdEquipamentoDetalhado() {
        return idEquipamentoDetalhado;
    }

    public void setIdEquipamentoDetalhado(Integer idEquipamentoDetalhado) {
        this.idEquipamentoDetalhado = idEquipamentoDetalhado;
    }

    public UploadedFile getFiless() {
        return filess;
    }

    public void setFiless(UploadedFile filess) {
        this.filess = filess;
    }

    public List<File> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<File> arquivos) {
        this.arquivos = arquivos;
    }

    public File getFileRemove() {
        return fileRemove;
    }

    public void setFileRemove(File fileRemove) {
        this.fileRemove = fileRemove;
    }

}
