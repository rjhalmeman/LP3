/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedido.findByDataPedido", query = "SELECT p FROM Pedido p WHERE p.dataPedido = :dataPedido"),
    @NamedQuery(name = "Pedido.findByNaoSei", query = "SELECT p FROM Pedido p WHERE p.naoSei = :naoSei")})
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
    @Column(name = "naoSei")
    private String naoSei;
    @JoinColumn(name = "cliente_pessoa_cpf_pessoa", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Cliente clientePessoaCpfPessoa;
    @JoinColumn(name = "funcionario_pessoa_cpf_pessoa", referencedColumnName = "pessoa_cpf_pessoa")
    @ManyToOne(optional = false)
    private Funcionario funcionarioPessoaCpfPessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<PedidoHasProduto> pedidoHasProdutoList;

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

    public String getNaoSei() {
        return naoSei;
    }

    public void setNaoSei(String naoSei) {
        this.naoSei = naoSei;
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

    public List<PedidoHasProduto> getPedidoHasProdutoList() {
        return pedidoHasProdutoList;
    }

    public void setPedidoHasProdutoList(List<PedidoHasProduto> pedidoHasProdutoList) {
        this.pedidoHasProdutoList = pedidoHasProdutoList;
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
