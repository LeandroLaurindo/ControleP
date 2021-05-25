/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.Dids;
import br.com.cblink.controlep.jpa.ImportacaoCsvJpaDAO;
import br.com.cblink.controlep.util.Util;
import br.com.cblink.controlep.xml.Col;
import br.com.cblink.controlep.xml.LocalizaColuna;
import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Leandro Laurindo
 */
@Named
@ViewScoped
public class ImportacaoBean implements Serializable {

    private ImportacaoCsvJpaDAO csvJpaDAO = new ImportacaoCsvJpaDAO();
    private String path;
    private List<String> access;
    private String selectAccess;
    private List<Col> colunas;
    private String csvDivisorPercebido;
    private File arquivo;
    private List<SelectItem> cars;
    private String tipoArquivo = "PRINCIPAL";

    private List<String[]> listaGeral = new ArrayList<>();
    long tempoInicio = System.currentTimeMillis();
    private String arquivoCSV;
    private Integer idImportacao = 0;
    private int CPF = -1;
    public static Integer progress = 0;
    private int count;
    private int limit = 100;
    private int contlinhas = 0;
    private int divisorlinhas = 0;
    private int numeroLista = 1;
    private int contador = 0;
    private int countGeral = 0;
    private List<String[]> lista1 = new ArrayList<>();
    private List<String[]> lista2 = new ArrayList<>();
    private int pro = 0;
    private int cont = 0;
    public static List<String> histList = new ArrayList();
    private int NUMERO = -1;
    private int OPERADORA = -1;
    private int NOME = -1;
    private int DATAPORATABILIDADE = -1;
    private String fone = "";

    @PostConstruct
    public void init() {
        SelectItemGroup g1 = new SelectItemGroup("Dids");
        g1.setSelectItems(new SelectItem[]{
            new SelectItem("numero", "numero"), new SelectItem("operadora", "operadora"), new SelectItem("nome", "nome"), new SelectItem("dataPortabilidade", "dataPortabilidade")});

        setCars(new ArrayList<SelectItem>());
        getCars().add(g1);
    }

    public void fileUploadAction(FileUploadEvent event) {
        try {
            FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

            String realPath = context.getRealPath("/");

            // Aqui cria o diretorio caso não exista
            File file = new File(realPath + "/uploads/");
            file.mkdirs();

            //System.out.println("path:" + file.getAbsolutePath());
            byte[] arquivo = event.getFile().getContents();
            String caminho = realPath + "/uploads/" + event.getFile().getFileName();
            this.path = caminho;
            // esse trecho grava o arquivo no diretório
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();
            event.getFile().getInputstream().close();

            try {
                List<Col> colunas = new ArrayList();

                if (!caminho.contains(".csv")) {
                    //access    
                    //System.out.println("carregando...               " + path);
                    Set<String> systemTableNames = Database.open(new File(caminho)).getTableNames();
                    this.setAccess((List<String>) new ArrayList());
                    this.setSelectAccess("");
                    for (String a : systemTableNames) {
                        this.getAccess().add(a);
                        //System.out.println(a);
                    }

                    Database.open(new File(caminho)).close();
                    org.primefaces.PrimeFaces.current().ajax().update("accessfm");
                    org.primefaces.PrimeFaces.current().executeScript("PF('access').show()");
                } else {
                    try {
                        //System.out.println("aqui");
                        this.setColunas((List<Col>) new ArrayList());
                        String arquivoCSV = this.path;
                        BufferedReader br = null;
                        String linha = "";
                        br = new BufferedReader(new FileReader(arquivoCSV));
                        linha = br.readLine();

                        if (linha.contains(";")) {
                            this.csvDivisorPercebido = ";";
                        } else {
                            this.csvDivisorPercebido = ",";
                        }
                        //System.out.println("divisor percebido: " + csvDivisorPercebido);
                        String[] ln = linha.split(csvDivisorPercebido);

                        int col = ln.length;
                        //System.out.println("colunas: " + col);
                        for (int i = 0; i < col; i++) {
                            Col colun = new Col();
                            colun.setCampoAssociado(LocalizaColuna.colunaLocalizadorPrincipal(ln[i].trim()));
                            colun.setColuna(ln[i]);
                            colun.setIndice(i);

                            colunas.add(colun);
                            this.getColunas().add(colun);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    org.primefaces.PrimeFaces.current().ajax().update("form6");
                    org.primefaces.PrimeFaces.current().executeScript("PF('planilhaCamposAde').show()");
                }

            } catch (IOException ex) {
                Logger.getLogger(ImportacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            //System.out.println("Erro no upload de imagem" + ex);
        }
    }

    public void tabelaAssociacao() {
        if (true) {
            try {
                List<Col> colunas = new ArrayList();

                if (!this.path.contains(".csv")) {
                    this.setColunas((List<Col>) new ArrayList());
                    Table tb = Database.open(new File(this.path)).getTable(this.getSelectAccess());
                    List<Column> tabelas = tb.getColumns();

                    int cont = 0;
                    for (Column k : tabelas) {
                        Col colun = new Col();
                        colun.setCampoAssociado(LocalizaColuna.colunaLocalizadorPrincipal(k.getName()));
                        colun.setColuna(k.getName());
                        colun.setIndice(cont);
                        colunas.add(colun);
                        cont++;
                        this.getColunas().add(colun);
                    }
                    Database.open(new File(this.path)).close();
                } else {
                    String arquivoCSV = this.path;
                    BufferedReader br = null;
                    String linha = "";
                    String csvDivisor = ",";
                    br = new BufferedReader(new FileReader(arquivoCSV));
                    linha = br.readLine();

                    String[] ln = linha.split(csvDivisor);

                    int col = ln.length;

                    for (int i = 0; i < col; i++) {
                        Col colun = new Col();
                        colun.setCampoAssociado(LocalizaColuna.colunaLocalizadorDids(ln[i]));
                        colun.setColuna(ln[i]);
                        colun.setIndice(i);
                        colunas.add(colun);
                        this.getColunas().add(colun);
                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ImportacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ImportacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void refreshImport() {
        this.setColunas(null);
        this.setTipoArquivo(null);
        org.primefaces.PrimeFaces.current().ajax().update("Progr");
        org.primefaces.PrimeFaces.current().ajax().update("accessfm");
        org.primefaces.PrimeFaces.current().ajax().update("form6");
        org.primefaces.PrimeFaces.current().executeScript("PF('planilhaCamposConc').show()");
    }

    public void leitorInsert() throws IOException, InterruptedException {
        if (this.path.contains(".csv")) {
            arquivoCSV = this.path;
            BufferedReader br = null;
            BufferedReader brline = null;
            String linha = "";
            String csvDivisor = csvDivisorPercebido;//",";
            brline = new BufferedReader(new FileReader(arquivoCSV));
            br = new BufferedReader(new FileReader(arquivoCSV));

            for (Col c : this.getColunas()) {
                if (c.getCampoAssociado() != null) {
                    if ("numero".equalsIgnoreCase(c.getCampoAssociado())) {
                        NUMERO = c.getIndice();
                    }
                    if ("operadora".equalsIgnoreCase(c.getCampoAssociado())) {
                        OPERADORA = c.getIndice();
                    }
                    if ("nome".equalsIgnoreCase(c.getCampoAssociado())) {
                        NOME = c.getIndice();
                    }
                    if ("dataPortabilidade".equalsIgnoreCase(c.getCampoAssociado())) {
                        DATAPORATABILIDADE = c.getIndice();
                    }
                }
            }
            progress = 0;
            count = (int) brline.lines().count();
            limit = contlinhas = count / 1;
            divisorlinhas = count / 1;
            if (divisorlinhas == 0) {
                divisorlinhas = count;
                numeroLista = 1;
            }
            //System.out.println("Linhas: " + count);
            //System.out.println("Divisor: " + divisorlinhas);

            boolean primeiralinhas = true;
            //antigo = importacaoArquivoDAO.qtdeLinhas("CadCliente").intValue();
            //antigoMat = importacaoArquivoDAO.qtdeLinhas("CadOrgao").intValue();

            tempoInicio = System.currentTimeMillis();

            while (numeroLista <= 1) {
                while (contador < divisorlinhas) {
                    //System.out.println("Contador: " + contador);
                    //while ((linha = br.readLine()) != null) {
                    linha = br.readLine();

                    if (primeiralinhas) {
                        primeiralinhas = false;
                        progress++;
                        contador++;
                        countGeral++;
                        //System.out.println("pula primeira linha");
                        continue;
                    } else {
                        //System.out.println("segunda sequencia");
                    }
                    if (linha != null) {
                        String[] ln = linha.split(csvDivisor);
                        listaGeral.add(ln);
                    } else {
                        countGeral++;
                        break;
                    }
                    contador++;
                    //}
                }
                countGeral = countGeral + listaGeral.size();
                populaLista(numeroLista, listaGeral);
                listaGeral = new ArrayList<String[]>();
                numeroLista++;
                contador = 0;
                //System.out.println("Tamanho total: " + count);
                //System.out.println("Tamanho inserio: " + countGeral);
                //System.out.println("Numero Lista: " + numeroLista);
                if (numeroLista == 2) {
                    if (countGeral < count) {
                        numeroLista = 1;
                    }
                }
            }

            //System.out.println("Tamanho total: " + count);
            //System.out.println("Tamanho inserio: " + countGeral);
            try {

                for (String[] linha2 : lista1) {
                    //System.out.println("Passou aqui Timer 3");
                    insereLinhas(linha2);
                }
                progress = 0;

                int aposInsert = 0;
                int aposInsertMat = 0;
                int limitPesq = 0;

                long milliseconds = (System.currentTimeMillis() - tempoInicio);
                Util.criarMensagemAviso("Importados com Sucesso!");
            } catch (Exception e) { // Thread foi interrompida por alguma excessão lançada
                //e.printStackTrace();
            }
        }
    }

    public void populaLista(int numero, List<String[]> lista) {
        //System.out.println("Popula lista: " + numero + " " + lista.size());
        switch (numero) {
            case 1:
                lista1 = lista;
                break;
            case 2:
                if (lista2.size() > 0) {
                    for (String[] l : lista) {
                        lista2.add(l);
                    }
                } else {
                    lista2 = lista;
                }
                break;

            default:
                break;
        }
    }

    public void insereLinhas(String[] ln) {

        try {
            cont++;
            pro++;

            if (pro >= limit) {
                progress++;//(Integer) Math.round(((float) cont / linhasa) * 100);
                if (progress == 99) {
                    //org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('progresso').hide()");
                }
                pro = 0;
            }

            if (true) {
                Dids dids = new Dids();
                boolean temNumero = false;
                try {
                    if (NUMERO > -1) {
                        if (NUMERO < ln.length) {
                            if (existLinha(ln[NUMERO].replace("\"", ""))) {
                                dids.setNumero(ln[NUMERO]);
                                fone = ln[NUMERO];
                                temNumero = true;
                            }
                        }
                    }
                    if (OPERADORA > -1) {
                        //System.out.println("CPF:" + CPF);
                        if (OPERADORA < ln.length) {
                            if (existLinha(ln[OPERADORA].replace("\"", ""))) {
                                dids.setOperadora(ln[OPERADORA]);
                            }
                        }
                    }
                    if (NOME > -1) {
                        //System.out.println("CPF:" + CPF);
                        if (NUMERO < ln.length) {
                            if (existLinha(ln[NOME].replace("\"", ""))) {
                                dids.setNome(ln[NOME]);
                            }
                        }
                    }
                    if (DATAPORATABILIDADE > -1) {
                        //System.out.println("CPF:" + CPF);
                        if (DATAPORATABILIDADE < ln.length) {
                            if (existLinha(ln[DATAPORATABILIDADE].replace("\"", ""))) {
                                dids.setDataPortabilidade(formataDataPadraoBDComHoras(ln[DATAPORATABILIDADE]));
                            }
                        }
                    }
                    if (!temNumero) {
                        dids.setNumero(fone);
                    }
                    csvJpaDAO.create(dids);

                } catch (Exception e) {
                    e.printStackTrace();
                    if (e.toString().contains("ArrayIndexOutOfBoundsException")) {
                        FacesMessage message = new FacesMessage("Erro:" + e);
                        FacesContext.getCurrentInstance().addMessage("growltop", message);

                         org.primefaces.PrimeFaces.current().executeScript("PF('progresso').hide()");
                         org.primefaces.PrimeFaces.current().ajax().update("Tables");
                        progress = 0;
                        //break;
                    }
                } finally {

                }
            }
        } catch (IndexOutOfBoundsException | HeadlessException e) {
            FacesMessage message = new FacesMessage("Linhas com benefício inexistente. Linhas sem número de benefício não serão inseridas na base");
            FacesContext.getCurrentInstance().addMessage(null, message);
            org.primefaces.PrimeFaces.current().executeScript("PF('progresso').hide()");
             org.primefaces.PrimeFaces.current().ajax().update("Tables");
            //System.out.println("forLeitor:" + e);
            progress = 0;
            e.printStackTrace();
        } finally {

        }
    }

    public static boolean existLinha(String row) {
        if (!row.isEmpty()) {// && !row.equalsIgnoreCase("null")) {
            ////System.out.println("CONTEM NA CELULA:" + row);
            return true;
        } else {
            ////System.out.println("ELSE CONTEM NA CELULA:" + row);
            return false;
        }
    }

    public void startBar() {
        if (progress > 0) {
            org.primefaces.PrimeFaces.current().executeScript("PF('pbAjax').start()");
        } else {
            //System.out.println("bp < zero");
        }
    }

    public List<String> getAccess() {
        return access;
    }

    public void setAccess(List<String> access) {
        this.access = access;
    }

    public String getSelectAccess() {
        return selectAccess;
    }

    public void setSelectAccess(String selectAccess) {
        this.selectAccess = selectAccess;
    }

    public List<Col> getColunas() {
        return colunas;
    }

    public void setColunas(List<Col> colunas) {
        this.colunas = colunas;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public List<SelectItem> getCars() {
        return cars;
    }

    public void setCars(List<SelectItem> cars) {
        this.cars = cars;
    }

    public String formataDataNasc(Date dataParam) {
        if (dataParam == null || dataParam.equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(dataParam);

        return data;
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
}
