/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.xml;

/**
 *
 * @author Leandro Laurindo
 */
public class LocalizaColuna {

    public static String colunaLocalizadorDids(String coluna) {
        if (coluna.equalsIgnoreCase("NUMERO")) {
            return "NUMERO";
        } else if (coluna.equalsIgnoreCase("OPERADORA")) {
            return "OPERADORA";
        } else if (coluna.equalsIgnoreCase("DATA_PORTABILIDADE")) {
            return "DATA_PORTABILIDADE";
        } else if (coluna.equalsIgnoreCase("NOME")) {
            return "NOME";
        } else if (coluna.equalsIgnoreCase("id")) {
            return "id";
        }
        return "selecione";
    }

    public static String colunaLocalizadorPrincipal(String coluna) {
        if (coluna.contains("numero")) {
            return "numero";
        } else if (coluna.contains("operadora")) {
            return "operadora";
        } else if (coluna.contains("data_portabilidade")) {
            return "data_portabilidade";
        } else if (coluna.contains("nome")) {
            return "nome";
        } else if (coluna.contains("id")) {
            return "id";
        }
        return "selecione";
    }

    public static String colunaLocalizadorDidsFsm(String coluna) {
        if (coluna.equalsIgnoreCase("NUMERO")) {
            return "NUMERO";
        } else if (coluna.equalsIgnoreCase("OPERADORA")) {
            return "OPRADORA";
        } else if (coluna.equalsIgnoreCase("STATUS")) {
            return "STATUS";
        } else if (coluna.equalsIgnoreCase("DONO")) {
            return "DONO";
        } else if (coluna.equalsIgnoreCase("RAMAL")) {
            return "RAMAL";
        } else if (coluna.equalsIgnoreCase("id")) {
            return "id";
        }
        return "selecione";
    }

    public static String colunaLocalizadorPrincipalDidsFsm(String coluna) {
        if (coluna.contains("numero")) {
            return "numero";
        } else if (coluna.contains("operadora")) {
            return "operadora";
        } else if (coluna.contains("status")) {
            return "status";
        } else if (coluna.contains("dono")) {
            return "dono";
        } else if (coluna.contains("ramal")) {
            return "ramal";
        } else if (coluna.contains("id")) {
            return "id";
        }
        return "selecione";
    }

    public static String colunaLocalizadorClientes(String coluna) {
        if (coluna.equalsIgnoreCase("NOME")) {
            return "NOME";
        } else if (coluna.equalsIgnoreCase("CPFCNPJ")) {
            return "CPFCNPJ";
        } else if (coluna.equalsIgnoreCase("TELEFONE")) {
            return "TELEFONE";
        } else if (coluna.equalsIgnoreCase("LOGIN")) {
            return "LOGIN";
        } else if (coluna.equalsIgnoreCase("PLATAFORMA")) {
            return "PLATAFORMA";
        } else if (coluna.equalsIgnoreCase("id")) {
            return "id";
        }
        return "selecione";
    }

    public static String colunaLocalizadorPrincipalClientes(String coluna) {
        if (coluna.contains("nome")) {
            return "nome";
        } else if (coluna.contains("cpf_cnpj")) {
            return "cpf_cnpj";
        } else if (coluna.contains("telefone")) {
            return "telefone";
        } else if (coluna.contains("login")) {
            return "login";
        } else if (coluna.contains("plataforma")) {
            return "plataforma";
        } else if (coluna.contains("id")) {
            return "id";
        }
        return "selecione";
    }

    public static String colunaLocalizadorTelefones(String coluna) {
        if (coluna.contains("NUMERO")) {
            return "NUMERO";
        } else if (coluna.contains("id_telefone")) {
            return "id_telefone";
        }
        return "selecione";
    }

    public static String colunaLocalizadorPrincipalTelefones(String coluna) {
        if (coluna.contains("numero")) {
            return "numero";
        } else if (coluna.contains("id_telefone")) {
            return "id_telefone";
        }
        return "selecione";
    }
}
