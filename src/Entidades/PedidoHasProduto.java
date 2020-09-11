/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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
@Table(name = "pedido_has_produto")
@NamedQueries({
    @NamedQuery(name = "PedidoHasProduto.findAll", query = "SELECT p FROM PedidoHasProduto p")})
public class PedidoHasProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoHasProdutoPK pedidoHasProdutoPK;
    @Basic(optional = false)
    @Column(name = "quantidade_produto_pedido")
    private int quantidadeProdutoPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_unitario")
    private Double precoUnitario;
    @Column(name = "desconto_unitario")
    private Double descontoUnitario;
    @JoinColumn(name = "pedido_id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public PedidoHasProduto() {
    }

    public PedidoHasProduto(PedidoHasProdutoPK pedidoHasProdutoPK) {
        this.pedidoHasProdutoPK = pedidoHasProdutoPK;
    }

    public PedidoHasProduto(PedidoHasProdutoPK pedidoHasProdutoPK, int quantidadeProdutoPedido) {
        this.pedidoHasProdutoPK = pedidoHasProdutoPK;
        this.quantidadeProdutoPedido = quantidadeProdutoPedido;
    }

    public PedidoHasProduto(int pedidoIdPedido, int produtoIdProduto) {
        this.pedidoHasProdutoPK = new PedidoHasProdutoPK(pedidoIdPedido, produtoIdProduto);
    }

    public PedidoHasProdutoPK getPedidoHasProdutoPK() {
        return pedidoHasProdutoPK;
    }

    public void setPedidoHasProdutoPK(PedidoHasProdutoPK pedidoHasProdutoPK) {
        this.pedidoHasProdutoPK = pedidoHasProdutoPK;
    }

    public int getQuantidadeProdutoPedido() {
        return quantidadeProdutoPedido;
    }

    public void setQuantidadeProdutoPedido(int quantidadeProdutoPedido) {
        this.quantidadeProdutoPedido = quantidadeProdutoPedido;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getDescontoUnitario() {
        return descontoUnitario;
    }

    public void setDescontoUnitario(Double descontoUnitario) {
        this.descontoUnitario = descontoUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        hash += (pedidoHasProdutoPK != null ? pedidoHasProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoHasProduto)) {
            return false;
        }
        PedidoHasProduto other = (PedidoHasProduto) object;
        if ((this.pedidoHasProdutoPK == null && other.pedidoHasProdutoPK != null) || (this.pedidoHasProdutoPK != null && !this.pedidoHasProdutoPK.equals(other.pedidoHasProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoHasProduto[ pedidoHasProdutoPK=" + pedidoHasProdutoPK + " ]";
    }

}
