/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "unidade_de_medida")
@NamedQueries({
    @NamedQuery(name = "UnidadeDeMedida.findAll", query = "SELECT u FROM UnidadeDeMedida u"),
    @NamedQuery(name = "UnidadeDeMedida.findByIdUnidadeDeMedida", query = "SELECT u FROM UnidadeDeMedida u WHERE u.idUnidadeDeMedida = :idUnidadeDeMedida"),
    @NamedQuery(name = "UnidadeDeMedida.findByNomeUnidadeDeMedida", query = "SELECT u FROM UnidadeDeMedida u WHERE u.nomeUnidadeDeMedida = :nomeUnidadeDeMedida")})
public class UnidadeDeMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_unidade_de_medida")
    private String idUnidadeDeMedida;
    @Basic(optional = false)
    @Column(name = "nome_unidade_de_medida")
    private String nomeUnidadeDeMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeDeMedidaIdUnidadeDeMedida")
    private List<Produto> produtoList;

    public UnidadeDeMedida() {
    }

    public UnidadeDeMedida(String idUnidadeDeMedida) {
        this.idUnidadeDeMedida = idUnidadeDeMedida;
    }

    public UnidadeDeMedida(String idUnidadeDeMedida, String nomeUnidadeDeMedida) {
        this.idUnidadeDeMedida = idUnidadeDeMedida;
        this.nomeUnidadeDeMedida = nomeUnidadeDeMedida;
    }

    public String getIdUnidadeDeMedida() {
        return idUnidadeDeMedida;
    }

    public void setIdUnidadeDeMedida(String idUnidadeDeMedida) {
        this.idUnidadeDeMedida = idUnidadeDeMedida;
    }

    public String getNomeUnidadeDeMedida() {
        return nomeUnidadeDeMedida;
    }

    public void setNomeUnidadeDeMedida(String nomeUnidadeDeMedida) {
        this.nomeUnidadeDeMedida = nomeUnidadeDeMedida;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadeDeMedida != null ? idUnidadeDeMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeDeMedida)) {
            return false;
        }
        UnidadeDeMedida other = (UnidadeDeMedida) object;
        if ((this.idUnidadeDeMedida == null && other.idUnidadeDeMedida != null) || (this.idUnidadeDeMedida != null && !this.idUnidadeDeMedida.equals(other.idUnidadeDeMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.UnidadeDeMedida[ idUnidadeDeMedida=" + idUnidadeDeMedida + " ]";
    }
    
}
