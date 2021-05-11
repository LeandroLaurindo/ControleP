/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cblink.controlep.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leandro Laurindo
 */
@Entity
@Table(name = "cad_equipamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadEquipamentos.findAll", query = "SELECT c FROM CadEquipamentos c")})
public class CadEquipamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_equipamentos")
    private Integer idEquipamentos;
    @Size(max = 250)
    @Column(name = "nome")
    private String nome;
    @Size(max = 250)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 100)
    @Column(name = "ip_equipamento")
    private String ipEquipamento;
    @Size(max = 250)
    @Column(name = "fabricante")
    private String fabricante;
    @Size(max = 250)
    @Column(name = "local")
    private String local;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cliente")
    private int codCliente;
    @Column(name = "data_equipamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEquipamento;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_fornecedor")
    private Integer idFornecedor;
    @Size(max = 2147483647)
    @Column(name = "observacoes")
    private String observacoes;

    public CadEquipamentos() {
    }

    public CadEquipamentos(Integer idEquipamentos) {
        this.idEquipamentos = idEquipamentos;
    }

    public CadEquipamentos(Integer idEquipamentos, int codCliente) {
        this.idEquipamentos = idEquipamentos;
        this.codCliente = codCliente;
    }

    public Integer getIdEquipamentos() {
        return idEquipamentos;
    }

    public void setIdEquipamentos(Integer idEquipamentos) {
        this.idEquipamentos = idEquipamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getIpEquipamento() {
        return ipEquipamento;
    }

    public void setIpEquipamento(String ipEquipamento) {
        this.ipEquipamento = ipEquipamento;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public Date getDataEquipamento() {
        return dataEquipamento;
    }

    public void setDataEquipamento(Date dataEquipamento) {
        this.dataEquipamento = dataEquipamento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipamentos != null ? idEquipamentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadEquipamentos)) {
            return false;
        }
        CadEquipamentos other = (CadEquipamentos) object;
        if ((this.idEquipamentos == null && other.idEquipamentos != null) || (this.idEquipamentos != null && !this.idEquipamentos.equals(other.idEquipamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cblink.controlep.entidades.CadEquipamentos[ idEquipamentos=" + idEquipamentos + " ]";
    }

}
