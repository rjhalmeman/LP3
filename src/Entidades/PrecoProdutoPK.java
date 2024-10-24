/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tools.CaixaDeFerramentas;

/**
 *
 * @author belly
 */
@Embeddable
public class PrecoProdutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto_id_produto")
    private int produtoIdProduto;
    @Basic(optional = false)
    @Column(name = "data_preco")
    @Temporal(TemporalType.DATE)
    private Date dataPreco;

    public PrecoProdutoPK() {
    }

    public PrecoProdutoPK(int produtoIdProduto, Date dataPreco) {
        this.produtoIdProduto = produtoIdProduto;
        this.dataPreco = dataPreco;
    }

    public int getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(int produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    public Date getDataPreco() {
        return dataPreco;
    }

    public void setDataPreco(Date dataPreco) {
        this.dataPreco = dataPreco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produtoIdProduto;
        hash += (dataPreco != null ? dataPreco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecoProdutoPK)) {
            return false;
        }
        PrecoProdutoPK other = (PrecoProdutoPK) object;
        if (this.produtoIdProduto != other.produtoIdProduto) {
            return false;
        }
        if ((this.dataPreco == null && other.dataPreco != null) || (this.dataPreco != null && !this.dataPreco.equals(other.dataPreco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        return "Entidades.PrecoProdutoPK[ produtoIdProduto=" + produtoIdProduto + ", dataPrecoProduto= " + formato.format(dataPreco) + " ]";
    }

    public String getDataPrecoProdutoFormatado() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(getDataPreco());
    }

}
