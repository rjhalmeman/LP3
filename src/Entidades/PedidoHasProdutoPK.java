/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radames
 */
@Embeddable
public class PedidoHasProdutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto_id_produto")
    private int produtoIdProduto;
    @Basic(optional = false)
    @Column(name = "pedido_id_pedido")
    private int pedidoIdPedido;

    public PedidoHasProdutoPK() {
    }

    public PedidoHasProdutoPK(int produtoIdProduto, int pedidoIdPedido) {
        this.produtoIdProduto = produtoIdProduto;
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public int getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(int produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    public int getPedidoIdPedido() {
        return pedidoIdPedido;
    }

    public void setPedidoIdPedido(int pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produtoIdProduto;
        hash += (int) pedidoIdPedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoHasProdutoPK)) {
            return false;
        }
        PedidoHasProdutoPK other = (PedidoHasProdutoPK) object;
        if (this.produtoIdProduto != other.produtoIdProduto) {
            return false;
        }
        if (this.pedidoIdPedido != other.pedidoIdPedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoHasProdutoPK[ produtoIdProduto=" + produtoIdProduto + ", pedidoIdPedido=" + pedidoIdPedido + " ]";
    }
    
}
