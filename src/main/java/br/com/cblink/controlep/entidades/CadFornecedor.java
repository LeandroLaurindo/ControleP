/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leandro Laurindo
 */
@Entity
@Table(name = "cad_fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadFornecedor.findAll", query = "SELECT c FROM CadFornecedor c")})
public class CadFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fornecedor")
    private Integer idFornecedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nome")
    private String nome;
    @Size(max = 30)
    @Column(name = "documento_um")
    private String documentoUm;
    @Size(max = 30)
    @Column(name = "documento_dois")
    private String documentoDois;
    @Size(max = 250)
    @Column(name = "endereco")
    private String endereco;
    @Size(max = 20)
    @Column(name = "numero")
    private String numero;
    @Size(max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 100)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 2)
    @Column(name = "uf")
    private String uf;
    @Size(max = 15)
    @Column(name = "cep")
    private String cep;
    @Size(max = 30)
    @Column(name = "pais")
    private String pais;
    @Size(max = 30)
    @Column(name = "contato")
    private String contato;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "email")
    private String email;
    @Size(max = 15)
    @Column(name = "telefone_um")
    private String telefoneUm;
    @Size(max = 15)
    @Column(name = "telefone_dois")
    private String telefoneDois;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;

    public CadFornecedor() {
    }

    public CadFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public CadFornecedor(Integer idFornecedor, String nome) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoUm() {
        return documentoUm;
    }

    public void setDocumentoUm(String documentoUm) {
        this.documentoUm = documentoUm;
    }

    public String getDocumentoDois() {
        return documentoDois;
    }

    public void setDocumentoDois(String documentoDois) {
        this.documentoDois = documentoDois;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneUm() {
        return telefoneUm;
    }

    public void setTelefoneUm(String telefoneUm) {
        this.telefoneUm = telefoneUm;
    }

    public String getTelefoneDois() {
        return telefoneDois;
    }

    public void setTelefoneDois(String telefoneDois) {
        this.telefoneDois = telefoneDois;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFornecedor != null ? idFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFornecedor)) {
            return false;
        }
        CadFornecedor other = (CadFornecedor) object;
        if ((this.idFornecedor == null && other.idFornecedor != null) || (this.idFornecedor != null && !this.idFornecedor.equals(other.idFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cblink.controlep.entidades.CadFornecedor[ idFornecedor=" + idFornecedor + " ]";
    }

}
