/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.Clientes;
import br.com.cblink.controlep.entidades.Dids;
import br.com.cblink.controlep.entidades.Didsfsm;
import br.com.cblink.controlep.entidades.Telefones;
import br.com.cblink.controlep.jpa.ImportacaoCsvJpaDAO;
import br.com.cblink.controlep.util.ArquivoUtilImportacao;
import br.com.cblink.controlep.util.Util;
import br.com.cblink.controlep.vo.ColunasPlanilhaClientes;
import br.com.cblink.controlep.vo.DidsColunas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Leandro Laurindo
 */
@ManagedBean(name = "controleImportacaoCSV")
@ViewScoped
public class ControleImportacaoCSV implements Serializable {

    private Dids dids;
    private List<Dids> listaDeDados;
    private UploadedFile filess;
    private String linha = "";
    private BufferedReader conteudoCSV = null;
    private String csvSeparadorCampo = ";";
    private ImportacaoCsvJpaDAO csvJpaDAO = new ImportacaoCsvJpaDAO();

    private String nomeColuna;

    private List<DidsColunas> listaColulasBd;

    private List<ColunasPlanilhaClientes> listaColulasPlanilha;

    private List<String> listaColunasCliente;
    private List<String> listaColunasTelefones;
    private List<String> listaColunasDids;
    private List<String> listaColunasDidsFsm;

    private String caminhoFinal = "";

    private List<Clientes> listaParaSalvar;

    private List<Telefones> listaParaSalvarTelefones;

    private List<Dids> listaParaSalvarDids;

    private List<Didsfsm> listaParaSalvarDidsFsm;

    @PostConstruct
    public void init() {
        listaColulasBd = new ArrayList<>();
        listaColunasCliente = new ArrayList<>();
        listaColunasCliente = csvJpaDAO.busccarNomesColumas("clientes");
        listaColunasTelefones = new ArrayList<>();
        listaColunasTelefones = csvJpaDAO.busccarNomesColumas("telefones");
        listaColunasDids = new ArrayList<>();
        listaColunasDids = csvJpaDAO.busccarNomesColumas("dids");
        listaColunasDidsFsm = new ArrayList<>();
        listaColunasDidsFsm = csvJpaDAO.busccarNomesColumas("didsfsm");
    }

    public void prepararDados() {

        try {
            if (filess != null && filess.getFileName().contains(".csv")) {
                FacesContext aFacesContext = FacesContext.getCurrentInstance();
                ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
                ArquivoUtilImportacao.escrever(filess.getFileName(), filess.getContents());
                String realPath = context.getRealPath("/");
                String caminho = realPath + "/uploads/" + filess.getFileName();
                caminhoFinal = caminho;
                conteudoCSV = new BufferedReader(new FileReader(caminho));
                listaColulasPlanilha = new ArrayList<>();
                while ((linha = conteudoCSV.readLine()) != null) {
                    String[] dados = linha.split(csvSeparadorCampo);
                    for (int i = 0; i < dados.length; i++) {
                        listaColulasPlanilha.add(new ColunasPlanilhaClientes(i, dados[i]));
                    }
                    break;
                }

                Util.chamarFuncaoJs("PF('dlgLigacao').show()");

            } else {
                Util.criarMensagemAviso("O arquivo tem que ser formato CSV!");
            }
        } catch (IOException e) {

        }
    }

    public void prepararDadosDids() {

        try {
            if (filess != null && filess.getFileName().contains(".csv")) {
                FacesContext aFacesContext = FacesContext.getCurrentInstance();
                ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
                ArquivoUtilImportacao.escrever(filess.getFileName(), filess.getContents());
                String realPath = context.getRealPath("/");
                String caminho = realPath + "/uploads/" + filess.getFileName();
                caminhoFinal = caminho;
                conteudoCSV = new BufferedReader(new FileReader(caminho));
                listaColulasPlanilha = new ArrayList<>();
                while ((linha = conteudoCSV.readLine()) != null) {
                    String[] dados = linha.split(csvSeparadorCampo);
                    for (int i = 0; i < dados.length; i++) {
                        listaColulasPlanilha.add(new ColunasPlanilhaClientes(i, dados[i]));
                    }
                    break;
                }

                Util.chamarFuncaoJs("PF('dlgLigacaoT').show()");

            } else {
                Util.criarMensagemAviso("O arquivo tem que ser formato CSV!");
            }
        } catch (IOException e) {

        }
    }

    public void prepararDadosDidsFsm() {

        try {
            if (filess != null && filess.getFileName().contains(".csv")) {
                FacesContext aFacesContext = FacesContext.getCurrentInstance();
                ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
                ArquivoUtilImportacao.escrever(filess.getFileName(), filess.getContents());
                String realPath = context.getRealPath("/");
                String caminho = realPath + "/uploads/" + filess.getFileName();
                caminhoFinal = caminho;
                conteudoCSV = new BufferedReader(new FileReader(caminho));
                listaColulasPlanilha = new ArrayList<>();
                while ((linha = conteudoCSV.readLine()) != null) {
                    String[] dados = linha.split(csvSeparadorCampo);
                    for (int i = 0; i < dados.length; i++) {
                        listaColulasPlanilha.add(new ColunasPlanilhaClientes(i, dados[i]));
                    }
                    break;
                }

                Util.chamarFuncaoJs("PF('dlgLigacaoD').show()");

            } else {
                Util.criarMensagemAviso("O arquivo tem que ser formato CSV!");
            }
        } catch (IOException e) {

        }
    }

    public void metodoDeAssociacao(Integer i) {
        boolean inserir = true;

        for (DidsColunas didsColunas : listaColulasBd) {
            if (didsColunas.getColuna().equalsIgnoreCase(nomeColuna)) {
                inserir = false;
                break;
            }
        }
        if (inserir) {
            listaColulasBd.add(new DidsColunas(i, nomeColuna));
        } else {
            listaColulasBd = new ArrayList<>();
            Util.updateComponente("frmLigacao");
            nomeColuna = "";

            Util.chamarFuncaoJs("PF('dlgLigacao').show()");
            Util.criarMensagemErro("Por favor refaça a associação novamente!");
        }
    }

    public void metodoDeAssociacaoDids(Integer i) {
        boolean inserir = true;

        for (DidsColunas didsColunas : listaColulasBd) {
            if (didsColunas.getColuna().equalsIgnoreCase(nomeColuna)) {
                inserir = false;
                break;
            }
        }
        if (inserir) {
            listaColulasBd.add(new DidsColunas(i, nomeColuna));
        } else {
            listaColulasBd = new ArrayList<>();
            Util.updateComponente("frmLigacaof");
            nomeColuna = "";
            Util.chamarFuncaoJs("PF('dlgLigacaoT').show()");
            Util.criarMensagemErro("Por favor refaça a associação novamente!");
        }
    }

    public void metodoDeAssociacaoDidsFsm(Integer i) {
        boolean inserir = true;

        for (DidsColunas didsColunas : listaColulasBd) {
            if (didsColunas.getColuna().equalsIgnoreCase(nomeColuna)) {
                inserir = false;
                break;
            }
        }
        if (inserir) {
            listaColulasBd.add(new DidsColunas(i, nomeColuna));
        } else {
            listaColulasBd = new ArrayList<>();
            Util.updateComponente("frmLigacaod");
            nomeColuna = "";
            Util.chamarFuncaoJs("PF('dlgLigacaoD').show()");
            Util.criarMensagemErro("Por favor refaça a associação novamente!");
        }
    }

    public void importarCliente() {
        try {
            conteudoCSV = new BufferedReader(new FileReader(caminhoFinal));
            listaColulasPlanilha = new ArrayList<>();
            boolean primeiraLinha = true;
            listaParaSalvar = new ArrayList<>();
            while ((linha = conteudoCSV.readLine()) != null) {
                Clientes clientes = new Clientes();
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] dados = linha.split(csvSeparadorCampo);
                    for (int i = 0; i < dados.length; i++) {
                        for (DidsColunas dc : listaColulasBd) {
                            if (dc.getIndice() == i) {

                                if (dc.getColuna().equalsIgnoreCase("nome")) {
                                    clientes.setNome(dados[i]);
                                }
                                if (dc.getColuna().equalsIgnoreCase("cpf_cnpj")) {
                                    clientes.setCpfCnpj(dados[i]);
                                }

                                if (dc.getColuna().equalsIgnoreCase("telefone")) {
                                    clientes.setTelefone(dados[i]);
                                }
                                if (dc.getColuna().equalsIgnoreCase("login")) {
                                    clientes.setLogin(dados[i]);
                                }
                                if (dc.getColuna().equalsIgnoreCase("plataforma")) {
                                    clientes.setPlataforma(dados[i]);
                                }
                            }
                        }
                    }
                    listaParaSalvar.add(clientes);
                }

            }
        } catch (IOException e) {
            Util.criarMensagemErro("Erro ao importar arquivo" + e.getMessage());
        }
        if (listaParaSalvar.size() > 0) {
            csvJpaDAO.createMultiplusClientes(listaParaSalvar);
            Util.criarMensagemInfo("Arquivos Importados com sucesso!");
            Util.chamarFuncaoJs("PF('dlgLigacao').hide()");
        }
    }

    public void importarDids() {
        try {
            conteudoCSV = new BufferedReader(new FileReader(caminhoFinal));
            listaColulasPlanilha = new ArrayList<>();
            boolean primeiraLinha = true;
            listaParaSalvarDids = new ArrayList<>();
            String telefone = "";
            while ((linha = conteudoCSV.readLine()) != null) {
                Dids didsd = new Dids();
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] dados = linha.split(csvSeparadorCampo);

                    for (int i = 0; i < dados.length; i++) {
                        for (DidsColunas dc : listaColulasBd) {
                            if (dc.getIndice() == i) {
                                if (dc.getColuna().equalsIgnoreCase("data_portabilidade")) {
                                    if (dados[i] != null && !dados[i].isEmpty()) {
                                        didsd.setDataPortabilidade(formataDataPadraoBDComHoras(dados[i].trim()));
                                    } else {
                                        didsd.setDataPortabilidade(null);
                                    }

                                }
                                if (dc.getColuna().equalsIgnoreCase("nome")) {
                                    didsd.setNome(dados[i]);

                                }

                                if (dc.getColuna().equalsIgnoreCase("numero")) {
                                    if (dados[i] != null && !dados[i].isEmpty()) {
                                        telefone = dados[i].trim();
                                        didsd.setNumero(telefone);

                                    } else {
                                        didsd.setNumero(telefone);
                                    }

                                }
                                if (dc.getColuna().equalsIgnoreCase("operadora")) {
                                    didsd.setOperadora(dados[i]);

                                }
                            }
                        }
                    }

                    listaParaSalvarDids.add(didsd);
                }

            }
        } catch (Throwable e) {
            Util.criarMensagemErro("Erro ao importar arquivo" + e.getMessage());
        }

        if (listaParaSalvarDids.size() > 0) {
            csvJpaDAO.createMultiplus(listaParaSalvarDids);
            Util.criarMensagemInfo("DidsFsm Importados com sucesso!");
            Util.chamarFuncaoJs("PF('dlgLigacaoD').hide()");
        }
    }

    public void importarDidsFsm() {
        try {
            conteudoCSV = new BufferedReader(new FileReader(caminhoFinal));
            listaColulasPlanilha = new ArrayList<>();
            boolean primeiraLinha = true;
            listaParaSalvarDidsFsm = new ArrayList<>();
      
            while ((linha = conteudoCSV.readLine()) != null) {
                Didsfsm didsd = new Didsfsm();
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] dados = linha.split(csvSeparadorCampo);

                    for (int i = 0; i < dados.length; i++) {
                        for (DidsColunas dc : listaColulasBd) {
                            if (dc.getIndice() == i) {
                                if (dc.getColuna().equalsIgnoreCase("numero")) {
                                    didsd.setNumero(dados[i]);
                                }
                            }
                            if (dc.getColuna().equalsIgnoreCase("operadora")) {
                                didsd.setOperadora(dados[i]);

                            }

                            if (dc.getColuna().equalsIgnoreCase("status")) {
                                didsd.setStatus(dados[i]);
                            }

                            if (dc.getColuna().equalsIgnoreCase("dono")) {
                                didsd.setDono(dados[i]);
                            }
                            if (dc.getColuna().equalsIgnoreCase("ramal")) {
                                didsd.setRamal(dados[i]);
                            }
                        }
                    }

                    listaParaSalvarDidsFsm.add(didsd);
                }

            }
        } catch (Throwable e) {
            Util.criarMensagemErro("Erro ao importar arquivo" + e.getMessage());
        }

        if (listaParaSalvarDidsFsm.size() > 0) {
            csvJpaDAO.createMultiplusDidsFsm(listaParaSalvarDidsFsm);
            Util.criarMensagemInfo("Dids Importados com sucesso!");
            Util.chamarFuncaoJs("PF('dlgLigacaoT').hide()");
        }
    }

    public void importar() {
        listaDeDados = new ArrayList<>();
        try {
            if (filess != null && filess.getFileName().contains(".csv")) {

                ArquivoUtilImportacao.escrever(filess.getFileName(), filess.getContents());
                FacesContext aFacesContext = FacesContext.getCurrentInstance();
                ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

                String realPath = context.getRealPath("/");
                String caminho = realPath + "/uploads/" + filess.getFileName();
                conteudoCSV = new BufferedReader(new FileReader(caminho));
                String telefone = "";
                while ((linha = conteudoCSV.readLine()) != null) {
                    String[] dados = linha.split(csvSeparadorCampo);
                    dids = new Dids();
                    for (int i = 0; i < dados.length; i++) {

                        if (i == 0) {
                            if (dados[0] != null && !dados[0].isEmpty()) {
                                telefone = dados[i].trim();
                                dids.setNumero(telefone);
                            } else {
                                dids.setNumero(telefone);
                            }
                        } else {
                            if (i == 1) {
                                dids.setOperadora(dados[i].trim());
                            }

                            if (i == 2) {
                                dids.setNome(dados[i]);
                            }
                            if (i == 3) {
                                if (dados[i] != null && !dados[i].isEmpty()) {
                                    dids.setDataPortabilidade(formataDataPadraoBDComHoras(dados[i].trim()));
                                } else {
                                    dids.setDataPortabilidade(null);
                                }
                            }

                        }

                    }
                    listaDeDados.add(dids);
                }
            }

        } catch (Throwable ex) {
            Logger.getLogger(ControleImportacaoCSV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conteudoCSV != null) {
                try {
                    conteudoCSV.close();
                } catch (IOException e) {
                    Logger.getLogger(ControleImportacaoCSV.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        // System.err.println(listaDeDados.size());
        if (listaDeDados.size() > 0) {
            if (csvJpaDAO.createMultiplus(listaDeDados)) {
                Util.criarMensagemInfo("Todos os dados inseridos com sucesso!");
            } else {
                Util.criarMensagemErro("Aconteceu algum erro algum dado pode ter sido perdido!");
            }
        }
    }

    public Dids getDids() {
        return dids;
    }

    public void setDids(Dids dids) {
        this.dids = dids;
    }

    public List<Dids> getListaDeDados() {
        return listaDeDados;
    }

    public void setListaDeDados(List<Dids> listaDeDados) {
        this.listaDeDados = listaDeDados;
    }

    public UploadedFile getFiless() {
        return filess;
    }

    public void setFiless(UploadedFile filess) {
        this.filess = filess;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public BufferedReader getConteudoCSV() {
        return conteudoCSV;
    }

    public void setConteudoCSV(BufferedReader conteudoCSV) {
        this.conteudoCSV = conteudoCSV;
    }

    public String getCsvSeparadorCampo() {
        return csvSeparadorCampo;
    }

    public void setCsvSeparadorCampo(String csvSeparadorCampo) {
        this.csvSeparadorCampo = csvSeparadorCampo;
    }

    public Date formataDataPadraoBDComHoras(String data) {

        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;
        try {
            String[] data1 = data.split(" ");
            if (data1[1].isEmpty()) {
                data1[1] = data1[2];
            }
            if (data1[1].length() == 5) {
                data1[1] += ":00";
            }

            if (data.contains("/")) {
                String[] data2 = data1[0].split("/");
                data = data2[2] + "-" + data2[1] + "-" + data2[0] + " " + data1[1];
            } else {
                String[] data2 = data1[0].split("-");
                data = data2[2] + "-" + data2[1] + "-" + data2[0] + " " + data1[1];
            }
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = new Date(formatter.parse(data).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(ControleImportacaoCSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public List<DidsColunas> getListaColulasBd() {
        return listaColulasBd;
    }

    public void setListaColulasBd(List<DidsColunas> listaColulasBd) {
        this.listaColulasBd = listaColulasBd;
    }

    public List<ColunasPlanilhaClientes> getListaColulasPlanilha() {
        return listaColulasPlanilha;
    }

    public void setListaColulasPlanilha(List<ColunasPlanilhaClientes> listaColulasPlanilha) {
        this.listaColulasPlanilha = listaColulasPlanilha;
    }

    public String getNomeColuna() {
        return nomeColuna;
    }

    public void setNomeColuna(String nomeColuna) {
        this.nomeColuna = nomeColuna;
    }

    public List<String> getListaColunasCliente() {
        return listaColunasCliente;
    }

    public void setListaColunasCliente(List<String> listaColunasCliente) {
        this.listaColunasCliente = listaColunasCliente;
    }

    public List<String> getListaColunasTelefones() {
        return listaColunasTelefones;
    }

    public void setListaColunasTelefones(List<String> listaColunasTelefones) {
        this.listaColunasTelefones = listaColunasTelefones;
    }

    public List<Clientes> getListaParaSalvar() {
        return listaParaSalvar;
    }

    public void setListaParaSalvar(List<Clientes> listaParaSalvar) {
        this.listaParaSalvar = listaParaSalvar;
    }

    public List<Telefones> getListaParaSalvarTelefones() {
        return listaParaSalvarTelefones;
    }

    public void setListaParaSalvarTelefones(List<Telefones> listaParaSalvarTelefones) {
        this.listaParaSalvarTelefones = listaParaSalvarTelefones;
    }

    public List<String> getListaColunasDids() {
        return listaColunasDids;
    }

    public void setListaColunasDids(List<String> listaColunasDids) {
        this.listaColunasDids = listaColunasDids;
    }

    public List<Dids> getListaParaSalvarDids() {
        return listaParaSalvarDids;
    }

    public void setListaParaSalvarDids(List<Dids> listaParaSalvarDids) {
        this.listaParaSalvarDids = listaParaSalvarDids;
    }

    public List<String> getListaColunasDidsFsm() {
        return listaColunasDidsFsm;
    }

    public void setListaColunasDidsFsm(List<String> listaColunasDidsFsm) {
        this.listaColunasDidsFsm = listaColunasDidsFsm;
    }

    public List<Didsfsm> getListaParaSalvarDidsFsm() {
        return listaParaSalvarDidsFsm;
    }

    public void setListaParaSalvarDidsFsm(List<Didsfsm> listaParaSalvarDidsFsm) {
        this.listaParaSalvarDidsFsm = listaParaSalvarDidsFsm;
    }

}
