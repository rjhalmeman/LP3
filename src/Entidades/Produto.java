/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_produto")
    private Integer idProduto;
    @Basic(optional = false)
    @Column(name = "nome_produto")
    private String nomeProduto;
    @Column(name = "quantidade_estoque_produto")
    private Integer quantidadeEstoqueProduto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<PrecoProduto> precoProdutoCollection;
    @JoinColumn(name = "status_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private Status statusIdStatus;
    @JoinColumn(name = "unidade_de_medida_id_unidade_de_medida", referencedColumnName = "id_unidade_de_medida")
    @ManyToOne(optional = false)
    private UnidadeDeMedida unidadeDeMedidaIdUnidadeDeMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<PedidoHasProduto> pedidoHasProdutoCollection;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(Integer idProduto, String nomeProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidadeEstoqueProduto() {
        return quantidadeEstoqueProduto;
    }

    public void setQuantidadeEstoqueProduto(Integer quantidadeEstoqueProduto) {
        this.quantidadeEstoqueProduto = quantidadeEstoqueProduto;
    }

    public Collection<PrecoProduto> getPrecoProdutoCollection() {
        return precoProdutoCollection;
    }

    public void setPrecoProdutoCollection(Collection<PrecoProduto> precoProdutoCollection) {
        this.precoProdutoCollection = precoProdutoCollection;
    }

    public Status getStatusIdStatus() {
        return statusIdStatus;
    }

    public void setStatusIdStatus(Status statusIdStatus) {
        this.statusIdStatus = statusIdStatus;
    }

    public UnidadeDeMedida getUnidadeDeMedidaIdUnidadeDeMedida() {
        return unidadeDeMedidaIdUnidadeDeMedida;
    }

    public void setUnidadeDeMedidaIdUnidadeDeMedida(UnidadeDeMedida unidadeDeMedidaIdUnidadeDeMedida) {
        this.unidadeDeMedidaIdUnidadeDeMedida = unidadeDeMedidaIdUnidadeDeMedida;
    }

    public Collection<PedidoHasProduto> getPedidoHasProdutoCollection() {
        return pedidoHasProdutoCollection;
    }

    public void setPedidoHasProdutoCollection(Collection<PedidoHasProduto> pedidoHasProdutoCollection) {
        this.pedidoHasProdutoCollection = pedidoHasProdutoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Produto[ idProduto=" + idProduto + " ]";
    }

// inicio - adicionado pelo gerador rdmsFromClasseEntidade
// fim -  adicionado pelo gerador rdmsFromClasseEntidade
}
