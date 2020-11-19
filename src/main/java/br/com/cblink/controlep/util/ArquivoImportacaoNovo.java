package br.com.cblink.controlep.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Balcao
 */
public class ArquivoImportacaoNovo {

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

        File dir = new File(diretorioRaiz(), "arquivosuteis");
        dir.mkdirs();
        return dir;
    }

    public static File diretorioRaiz() {
        String realPath = System.getProperty("catalina.base");

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
