/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "preco_produto")
@NamedQueries({
    @NamedQuery(name = "PrecoProduto.findAll", query = "SELECT p FROM PrecoProduto p")})
public class PrecoProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrecoProdutoPK precoProdutoPK;
    @Basic(optional = false)
    @Column(name = "preco_produto")
    private double precoProduto;
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public PrecoProduto() {
    }

    public PrecoProduto(PrecoProdutoPK precoProdutoPK) {
        this.precoProdutoPK = precoProdutoPK;
    }

    public PrecoProduto(PrecoProdutoPK precoProdutoPK, double precoProduto) {
        this.precoProdutoPK = precoProdutoPK;
        this.precoProduto = precoProduto;
    }

    public PrecoProduto(int produtoIdProduto, Date dataPrecoProduto) {
        this.precoProdutoPK = new PrecoProdutoPK(produtoIdProduto, dataPrecoProduto);
    }

    public PrecoProdutoPK getPrecoProdutoPK() {
        return precoProdutoPK;
    }

    public void setPrecoProdutoPK(PrecoProdutoPK precoProdutoPK) {
        this.precoProdutoPK = precoProdutoPK;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (precoProdutoPK != null ? precoProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecoProduto)) {
            return false;
        }
        PrecoProduto other = (PrecoProduto) object;
        if ((this.precoProdutoPK == null && other.precoProdutoPK != null) || (this.precoProdutoPK != null && !this.precoProdutoPK.equals(other.precoProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PrecoProduto[ precoProdutoPK=" + precoProdutoPK + " ]";
    }

// inicio - adicionado pelo gerador rdmsFromClasseEntidade
// fim -  adicionado pelo gerador rdmsFromClasseEntidade
}
