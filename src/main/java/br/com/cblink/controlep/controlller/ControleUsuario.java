/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import br.com.cblink.controlep.entidades.Usuario;
import br.com.cblink.controlep.jpa.UsuarioJpaDAO;
import br.com.cblink.controlep.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro Laurindo
 */
@ManagedBean(name = "controleUsuario")
@ViewScoped
public class ControleUsuario implements Serializable {

    private Usuario usuario;
    private List<Usuario> listaUsuario;
    private UsuarioJpaDAO jpaController = new UsuarioJpaDAO();

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listar();
    }

    public void listar() {
        listaUsuario = new ArrayList<>();
        listaUsuario = jpaController.findUsuarioEntities();
        Util.updateComponente("tableFormu");
    }

    public void salvar() {
        if (usuario.getIdUsuario() == null) {
            usuario.setLogin(usuario.getLogin().toLowerCase().trim());
            usuario.setSenha(usuario.getSenha().toLowerCase().trim());
            if (jpaController.create(usuario)) {
                Util.criarMensagemInfo("Salvo com sucesso!");
                listar();
                usuario = new Usuario();
                Util.updateComponente("cadFrm");
            } else {
                Util.criarMensagemErro("Erro não foi possivel salvar!");
            }
        } else {
            if (jpaController.edit(usuario)) {
                Util.criarMensagemInfo("Editado com sucesso!");
                listar();
                usuario = new Usuario();
                Util.updateComponente("cadFrm");
            } else {
                Util.criarMensagemErro("Erro não foi possivel salvar!");
            }
        }

    }

    public void deletar() {

        if (jpaController.destroy(usuario.getIdUsuario())) {
            listaUsuario.remove(usuario);
            Util.criarMensagemInfo("Removido com sucesso!");
            Util.updateComponente("tableFormu");
        } else {
            Util.criarMensagemErro("Erro: não foi possivel remover!");
        }
    }

    public void novoUsuario() {
        usuario = new Usuario();
        Util.updateComponente("cadFrm");
    }

    public void setarUsuarioEditar(Usuario usu) {
        usuario = usu;
        Util.updateComponente("cadFrm");
    }

    public void setarUsuarioRemover(Usuario usu) {
        usuario = usu;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

}
