/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Basic(optional = false)
    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    @JoinColumn(name = "cliente_pessoa_cpf_pessoa", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Cliente clientePessoaCpfPessoa;
    @JoinColumn(name = "funcionario_pessoa_cpf_pessoa", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Funcionario funcionarioPessoaCpfPessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<PedidoHasProduto> pedidoHasProdutoCollection;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Integer idPedido, Date dataPedido) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getClientePessoaCpfPessoa() {
        return clientePessoaCpfPessoa;
    }

    public void setClientePessoaCpfPessoa(Cliente clientePessoaCpfPessoa) {
        this.clientePessoaCpfPessoa = clientePessoaCpfPessoa;
    }

    public Funcionario getFuncionarioPessoaCpfPessoa() {
        return funcionarioPessoaCpfPessoa;
    }

    public void setFuncionarioPessoaCpfPessoa(Funcionario funcionarioPessoaCpfPessoa) {
        this.funcionarioPessoaCpfPessoa = funcionarioPessoaCpfPessoa;
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
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Pedido[ idPedido=" + idPedido + " ]";
    }

}
