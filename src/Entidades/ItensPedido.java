

package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

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

@Entity
@Table(name = "itens_pedido")
@NamedQueries({
    @NamedQuery(name = "ItensPedido.findAll", query = "SELECT i FROM ItensPedido i")})
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItensPedidoPK itensPedidoPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "valor_unitario")
    private double valorUnitario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desconto")
    private Double desconto;
    @JoinColumn(name = "pedido_id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public ItensPedido() {
    }

    public ItensPedido(ItensPedidoPK itensPedidoPK) {
        this.itensPedidoPK = itensPedidoPK;
    }

    public ItensPedido(ItensPedidoPK itensPedidoPK, int quantidade, double valorUnitario) {
        this.itensPedidoPK = itensPedidoPK;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public ItensPedido(int pedidoIdPedido, int produtoIdProduto) {
        this.itensPedidoPK = new ItensPedidoPK(pedidoIdPedido, produtoIdProduto);
    }

    public ItensPedidoPK getItensPedidoPK() {
        return itensPedidoPK;
    }

    public void setItensPedidoPK(ItensPedidoPK itensPedidoPK) {
        this.itensPedidoPK = itensPedidoPK;
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
        hash += (itensPedidoPK != null ? itensPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensPedido)) {
            return false;
        }
        ItensPedido other = (ItensPedido) object;
        if ((this.itensPedidoPK == null && other.itensPedidoPK != null) || (this.itensPedidoPK != null && !this.itensPedidoPK.equals(other.itensPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ItensPedido[ itensPedidoPK=" + itensPedidoPK + " ]";
    }

}
