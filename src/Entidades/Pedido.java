/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import tools.CaixaDeFerramentas;

/**
 *
 * @author belly
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
    @Column(name = "datahora_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoraPedido;
    @JoinColumn(name = "cliente_pessoa_cpf_pessoa", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Cliente clientePessoaCpfPessoa;
    @JoinColumn(name = "funcionario_pessoa_cpf_pessoa1", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Funcionario funcionarioPessoaCpfPessoa1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<PedidoHasProduto> pedidoHasProdutoCollection;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Integer idPedido, Date datahoraPedido) {
        this.idPedido = idPedido;
        this.datahoraPedido = datahoraPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDatahoraPedido() {
        return datahoraPedido;
    }

    public void setDatahoraPedido(Date datahoraPedido) {
        this.datahoraPedido = datahoraPedido;
    }

    public Cliente getClientePessoaCpfPessoa() {
        return clientePessoaCpfPessoa;
    }

    public void setClientePessoaCpfPessoa(Cliente clientePessoaCpfPessoa) {
        this.clientePessoaCpfPessoa = clientePessoaCpfPessoa;
    }

    public Funcionario getFuncionarioPessoaCpfPessoa1() {
        return funcionarioPessoaCpfPessoa1;
    }

    public void setFuncionarioPessoaCpfPessoa1(Funcionario funcionarioPessoaCpfPessoa1) {
        this.funcionarioPessoaCpfPessoa1 = funcionarioPessoaCpfPessoa1;
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
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return idPedido + ";" + clientePessoaCpfPessoa + ";" + funcionarioPessoaCpfPessoa1 + ";" + cf.converteDeDateParaString(datahoraPedido);
    }

}
