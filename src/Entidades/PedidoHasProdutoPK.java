package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PedidoHasProdutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pedido_id_pedido")
    private int pedidoIdPedido;
    @Basic(optional = false)
    @Column(name = "produto_id_produto")
    private int produtoIdProduto;

    public PedidoHasProdutoPK() {
    }

    public PedidoHasProdutoPK(int pedidoIdPedido, int produtoIdProduto) {
        this.pedidoIdPedido = pedidoIdPedido;
        this.produtoIdProduto = produtoIdProduto;
    }

    public int getPedidoIdPedido() {
        return pedidoIdPedido;
    }

    public void setPedidoIdPedido(int pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public int getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(int produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedidoIdPedido;
        hash += (int) produtoIdProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoHasProdutoPK)) {
            return false;
        }
        PedidoHasProdutoPK other = (PedidoHasProdutoPK) object;
        if (this.pedidoIdPedido != other.pedidoIdPedido) {
            return false;
        }
        if (this.produtoIdProduto != other.produtoIdProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoHasProdutoPK[ pedidoIdPedido=" + pedidoIdPedido + ", produtoIdProduto=" + produtoIdProduto + " ]";
    }

}
