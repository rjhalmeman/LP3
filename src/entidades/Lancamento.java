

package entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lancamento")
@NamedQueries({
    @NamedQuery(name = "Lancamento.findAll", query = "SELECT l FROM Lancamento l")})
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_lancamento")
    private Integer idLancamento;
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLancamento;
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "conta_id_conta", referencedColumnName = "id_conta")
    @ManyToOne(optional = false)
    private Conta contaIdConta;
    @JoinColumn(name = "tipo_lancamento_id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoLancamento tipoLancamentoIdTipo;

    public Lancamento() {
    }

    public Lancamento(Integer idLancamento) {
        this.idLancamento = idLancamento;
    }

    public Integer getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Integer idLancamento) {
        this.idLancamento = idLancamento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Conta getContaIdConta() {
        return contaIdConta;
    }

    public void setContaIdConta(Conta contaIdConta) {
        this.contaIdConta = contaIdConta;
    }

    public TipoLancamento getTipoLancamentoIdTipo() {
        return tipoLancamentoIdTipo;
    }

    public void setTipoLancamentoIdTipo(TipoLancamento tipoLancamentoIdTipo) {
        this.tipoLancamentoIdTipo = tipoLancamentoIdTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLancamento != null ? idLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.idLancamento == null && other.idLancamento != null) || (this.idLancamento != null && !this.idLancamento.equals(other.idLancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Lancamento[ idLancamento=" + idLancamento + " ]";
    }

}
