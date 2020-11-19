/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
/**
 *
 * @author Balcao
 */
public class ArquivoUtilImportacao {
    public static File escrever(String name, byte[] contents) throws IOException {
        File file = new File(diretorioRaizParaArquivos(), name);
        OutputStream out = new FileOutputStream(file);
        out.write(contents);
        out.close();

        return file;
    }

    public static List<File> listar() {
        File dir = diretorioRaizParaArquivos();

        return Arrays.asList(dir.listFiles());
    }

    public static java.io.File diretorioRaizParaArquivos() {
        
        File dir = new File(diretorioRaiz(),"uploads");       
            dir.mkdirs();
        return dir;
    }
    
    public static File diretorioRaiz() {
         FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

            String realPath = context.getRealPath("/");

            // Aqui cria o diretorio caso não exista
            File file = new File(realPath);
            file.mkdirs();

            //System.out.println("path:" + file.getAbsolutePath());
           //byte[] arquivo = event.getFile().getContents();
         
        // Estamos utilizando um diretório dentro da pasta temporária. 
        // No seu projeto, imagino que queira mudar isso para algo como:
        // File dir = new File(System.getProperty("user.home"), "algaworks");
        //File dir = new File(System.getProperty("java.io.tmpdir"));
            //dir.mkdirs();
        return file;
    }
}
