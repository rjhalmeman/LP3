

package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedidoX")
@NamedQueries({
    @NamedQuery(name = "ItenspedidoX.findAll", query = "SELECT i FROM ItenspedidoX i")})
public class ItenspedidoX implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pedido_id_pedido")
    private Integer pedidoIdPedido;
    @Basic(optional = false)
    @Column(name = "produto_id_produto")
    private int produtoIdProduto;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "valor_unitario")
    private double valorUnitario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desconto")
    private Double desconto;

    public ItenspedidoX() {
    }

    public ItenspedidoX(Integer pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public ItenspedidoX(Integer pedidoIdPedido, int produtoIdProduto, int quantidade, double valorUnitario) {
        this.pedidoIdPedido = pedidoIdPedido;
        this.produtoIdProduto = produtoIdProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Integer getPedidoIdPedido() {
        return pedidoIdPedido;
    }

    public void setPedidoIdPedido(Integer pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public int getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(int produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoIdPedido != null ? pedidoIdPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItenspedidoX)) {
            return false;
        }
        ItenspedidoX other = (ItenspedidoX) object;
        if ((this.pedidoIdPedido == null && other.pedidoIdPedido != null) || (this.pedidoIdPedido != null && !this.pedidoIdPedido.equals(other.pedidoIdPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ItenspedidoX[ pedidoIdPedido=" + pedidoIdPedido + " ]";
    }

}
