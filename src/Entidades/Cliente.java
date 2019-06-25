package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_cpf_pessoa")
    private String pessoaCpfPessoa;
    @Basic(optional = false)
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "renda_cliente")
    private Double rendaCliente;
    @JoinColumn(name = "pessoa_cpf_pessoa", referencedColumnName = "cpf_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientePessoaCpfPessoa")
    private List<Pedido> pedidoList;

    public Cliente() {
    }

    public Cliente(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
    }

    public Cliente(String pessoaCpfPessoa, Date dataCadastro) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
        this.dataCadastro = dataCadastro;
    }

    public String getPessoaCpfPessoa() {
        return pessoaCpfPessoa;
    }

    public void setPessoaCpfPessoa(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getRendaCliente() {
        return rendaCliente;
    }

    public void setRendaCliente(Double rendaCliente) {
        this.rendaCliente = rendaCliente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaCpfPessoa != null ? pessoaCpfPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.pessoaCpfPessoa == null && other.pessoaCpfPessoa != null) || (this.pessoaCpfPessoa != null && !this.pessoaCpfPessoa.equals(other.pessoaCpfPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cliente[ pessoaCpfPessoa=" + pessoaCpfPessoa + " ]";
    }

}
