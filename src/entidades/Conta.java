

package entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

import java.io.Serializable;
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

@Entity
@Table(name = "conta")
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_conta")
    private Integer idConta;
    @Column(name = "nome_conta")
    private String nomeConta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo")
    private Double saldo;
    @Column(name = "limite")
    private Double limite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaIdContaOrigem")
    private List<Transferencia> transferenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaIdContaDestino")
    private List<Transferencia> transferenciaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaIdConta")
    private List<Lancamento> lancamentoList;
    @JoinColumn(name = "tipo_conta_id_tipo_conta", referencedColumnName = "id_tipo_conta")
    @ManyToOne(optional = false)
    private TipoConta tipoContaIdTipoConta;

    public Conta() {
    }

    public Conta(Integer idConta) {
        this.idConta = idConta;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public List<Transferencia> getTransferenciaList() {
        return transferenciaList;
    }

    public void setTransferenciaList(List<Transferencia> transferenciaList) {
        this.transferenciaList = transferenciaList;
    }

    public List<Transferencia> getTransferenciaList1() {
        return transferenciaList1;
    }

    public void setTransferenciaList1(List<Transferencia> transferenciaList1) {
        this.transferenciaList1 = transferenciaList1;
    }

    public List<Lancamento> getLancamentoList() {
        return lancamentoList;
    }

    public void setLancamentoList(List<Lancamento> lancamentoList) {
        this.lancamentoList = lancamentoList;
    }

    public TipoConta getTipoContaIdTipoConta() {
        return tipoContaIdTipoConta;
    }

    public void setTipoContaIdTipoConta(TipoConta tipoContaIdTipoConta) {
        this.tipoContaIdTipoConta = tipoContaIdTipoConta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConta != null ? idConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.idConta == null && other.idConta != null) || (this.idConta != null && !this.idConta.equals(other.idConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Conta[ idConta=" + idConta + " ]";
    }

}
