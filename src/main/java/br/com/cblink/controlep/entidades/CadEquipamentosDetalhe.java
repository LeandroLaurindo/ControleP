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
 * @author Filipe Miranda
 */
@Entity
@Table(name = "cad_equipamentos_detalhe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadEquipamentosDetalhe.findAll", query = "SELECT c FROM CadEquipamentosDetalhe c")})
public class CadEquipamentosDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_equip_det")
    private Integer idEquipDet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_servico")
    private String tipoServico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "detalhe_servico")
    private String detalheServico;
    @Column(name = "id_equipamento")
    private Integer idEquipamento;

    public CadEquipamentosDetalhe() {
    }

    public CadEquipamentosDetalhe(Integer idEquipDet) {
        this.idEquipDet = idEquipDet;
    }

    public CadEquipamentosDetalhe(Integer idEquipDet, String tipoServico, String detalheServico) {
        this.idEquipDet = idEquipDet;
        this.tipoServico = tipoServico;
        this.detalheServico = detalheServico;
    }

    public Integer getIdEquipDet() {
        return idEquipDet;
    }

    public void setIdEquipDet(Integer idEquipDet) {
        this.idEquipDet = idEquipDet;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDetalheServico() {
        return detalheServico;
    }

    public void setDetalheServico(String detalheServico) {
        this.detalheServico = detalheServico;
    }

    public Integer getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Integer idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipDet != null ? idEquipDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadEquipamentosDetalhe)) {
            return false;
        }
        CadEquipamentosDetalhe other = (CadEquipamentosDetalhe) object;
        if ((this.idEquipDet == null && other.idEquipDet != null) || (this.idEquipDet != null && !this.idEquipDet.equals(other.idEquipDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cblink.controlep.entidades.CadEquipamentosDetalhe[ idEquipDet=" + idEquipDet + " ]";
    }
    
}
