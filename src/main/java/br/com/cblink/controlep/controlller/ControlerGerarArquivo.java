/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.Dids;
import br.com.cblink.controlep.jpa.GerarArquivoDAO;
import br.com.cblink.controlep.util.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Leandro Laurindo
 */
@Named(value = "controlerGerarArquivo")
@ViewScoped
public class ControlerGerarArquivo implements Serializable {

    private GerarArquivoDAO arquivoDAO = new GerarArquivoDAO();

    private List<Dids> listaDids;

    @PostConstruct
    public void init() {
        listaDids = new ArrayList<>();
    }

    public void filtrarDados() {
        Util.chamarFuncaoJs("PF('dlgGeracao').show()");
        String jpql = "SELECT c FROM Dids c WHERE c.dataPortabilidade is not null ORDER BY c.numero";

        listaDids = arquivoDAO.listarRegistros(jpql);

        List<Dids> listaFinal = new ArrayList<>();

        Dids aux = new Dids();
        for (Dids d1 : listaDids) {
            if (aux.getId() == null) {
                aux = d1;
            } else {
                if (aux.getNumero().equals(d1.getNumero())) {
                    if (aux.getDataPortabilidade().getTime() < d1.getDataPortabilidade().getTime()) {
                        aux = d1;
                    }
                } else {
                    listaFinal.add(aux);
                    aux = d1;
                }
            }
        }
        
        if(listaFinal.size() > 0){
            gerarCSV(listaFinal);
        }else{
          Util.criarMensagemInfo("Não foi encontrado nenhum dado!");   
        }

    }

    public void gerarCSV(List<Dids> lista) {
        FileWriter writer = null;
        try {
            String caminho2 = System.getProperty("catalina.base");
            caminho2 += "/arquivosuteis/telefones.csv";
            new File(caminho2);
            writer = new FileWriter(caminho2);
            writer.append("Numero");
            writer.append(';');
            writer.append("OPERADORA");
            writer.append(';');
            writer.append("NOME");
            writer.append(';');
            writer.append("DATA_PORTABILIDADE");
            writer.append('\n');

            for (Dids dados : lista) {
                writer.append(dados.getNumero());
                writer.append(';');
                writer.append(dados.getOperadora());
                writer.append(';');
                writer.append(dados.getNome());
                writer.append(';');
                writer.append(formatarDataComHorasBR(dados.getDataPortabilidade()));
                writer.append('\n');
            }

            writer.flush();
            writer.close();
            Util.chamarFuncaoJs("PF('dlgGeracao').hide()");
            Util.criarMensagemInfo("arquivo gerado com sucesso. Esta na pasta (" + caminho2 + "/arquivosuteis)");
        } catch (IOException e) {
            Util.chamarFuncaoJs("PF('dlgGeracao').hide()");
            Util.criarMensagemErro("Deu erro na geração do arquivo : " + e.getMessage());
        }
    }

    public String formatarDataComHorasBR(Date dt) {

        if (dt != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("pt", "BR"));
            return sdf.format(dt);
        } else {
            return null;
        }
    }
}
