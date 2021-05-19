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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leandro Laurindo
 */
@Entity
@Table(name = "chamadosatendidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chamadosatendidos.findAll", query = "SELECT c FROM Chamadosatendidos c")})
public class Chamadosatendidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "solicitante")
    private String solicitante;
    @Size(max = 50)
    @Column(name = "telefone")
    private String telefone;
    @Size(max = 500)
    @Column(name = "descricaoservico")
    private String descricaoservico;
    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    @Size(max = 2147483647)
    @Column(name = "descricaoservico_novo")
    private String descricaoservicoNovo;
    @Size(max = 100)
    @Column(name = "assunto")
    private String assunto;
    @JoinColumn(name = "clientes_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientes clientesId;

    public Chamadosatendidos() {
    }

    public Chamadosatendidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricaoservico() {
        return descricaoservico;
    }

    public void setDescricaoservico(String descricaoservico) {
        this.descricaoservico = descricaoservico;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricaoservicoNovo() {
        return descricaoservicoNovo;
    }

    public void setDescricaoservicoNovo(String descricaoservicoNovo) {
        this.descricaoservicoNovo = descricaoservicoNovo;
    }
    
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Clientes getClientesId() {
        return clientesId;
    }

    public void setClientesId(Clientes clientesId) {
        this.clientesId = clientesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chamadosatendidos)) {
            return false;
        }
        Chamadosatendidos other = (Chamadosatendidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cblink.controlep.entidades.Chamadosatendidos[ id=" + id + " ]";
    }
    
}
