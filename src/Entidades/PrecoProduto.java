/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
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
 * @author belly
 */
@Entity
@Table(name = "preco_produto")
@NamedQueries({
    @NamedQuery(name = "PrecoProduto.findAll", query = "SELECT p FROM PrecoProduto p")})
public class PrecoProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrecoProdutoPK precoProdutoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
    private Double preco;
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public PrecoProduto() {
    }

    public PrecoProduto(PrecoProdutoPK precoProdutoPK) {
        this.precoProdutoPK = precoProdutoPK;
    }

    public PrecoProduto(int produtoIdProduto, Date dataPreco) {
        this.precoProdutoPK = new PrecoProdutoPK(produtoIdProduto, dataPreco);
    }

    public PrecoProdutoPK getPrecoProdutoPK() {
        return precoProdutoPK;
    }

    public void setPrecoProdutoPK(PrecoProdutoPK precoProdutoPK) {
        this.precoProdutoPK = precoProdutoPK;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
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

}
