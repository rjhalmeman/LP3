

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
@Table(name = "tipo_lancamento")
@NamedQueries({
    @NamedQuery(name = "TipoLancamento.findAll", query = "SELECT t FROM TipoLancamento t")})
public class TipoLancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private String idTipo;
    @Column(name = "nome_tipo")
    private String nomeTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoLancamentoIdTipo")
    private List<Lancamento> lancamentoList;

    public TipoLancamento() {
    }

    public TipoLancamento(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public List<Lancamento> getLancamentoList() {
        return lancamentoList;
    }

    public void setLancamentoList(List<Lancamento> lancamentoList) {
        this.lancamentoList = lancamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLancamento)) {
            return false;
        }
        TipoLancamento other = (TipoLancamento) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoLancamento[ idTipo=" + idTipo + " ]";
    }

}
