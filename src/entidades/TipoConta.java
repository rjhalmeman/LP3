

package entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_conta")
@NamedQueries({
    @NamedQuery(name = "TipoConta.findAll", query = "SELECT t FROM TipoConta t")})
public class TipoConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_conta")
    private Integer idTipoConta;
    @Column(name = "nome_tipo_conta")
    private String nomeTipoConta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoContaIdTipoConta")
    private List<Conta> contaList;

    public TipoConta() {
    }

    public TipoConta(Integer idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public Integer getIdTipoConta() {
        return idTipoConta;
    }

    public void setIdTipoConta(Integer idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public String getNomeTipoConta() {
        return nomeTipoConta;
    }

    public void setNomeTipoConta(String nomeTipoConta) {
        this.nomeTipoConta = nomeTipoConta;
    }

    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(List<Conta> contaList) {
        this.contaList = contaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoConta != null ? idTipoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConta)) {
            return false;
        }
        TipoConta other = (TipoConta) object;
        if ((this.idTipoConta == null && other.idTipoConta != null) || (this.idTipoConta != null && !this.idTipoConta.equals(other.idTipoConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoConta[ idTipoConta=" + idTipoConta + " ]";
    }

}
