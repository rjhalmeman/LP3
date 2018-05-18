

package entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "transferencia")
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t")})
public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_transferencia")
    private Integer idTransferencia;
    @Column(name = "data_transferencia")
    private String dataTransferencia;
    @JoinColumn(name = "conta_id_conta_origem", referencedColumnName = "id_conta")
    @ManyToOne(optional = false)
    private Conta contaIdContaOrigem;
    @JoinColumn(name = "conta_id_conta_destino", referencedColumnName = "id_conta")
    @ManyToOne(optional = false)
    private Conta contaIdContaDestino;

    public Transferencia() {
    }

    public Transferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(String dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public Conta getContaIdContaOrigem() {
        return contaIdContaOrigem;
    }

    public void setContaIdContaOrigem(Conta contaIdContaOrigem) {
        this.contaIdContaOrigem = contaIdContaOrigem;
    }

    public Conta getContaIdContaDestino() {
        return contaIdContaDestino;
    }

    public void setContaIdContaDestino(Conta contaIdContaDestino) {
        this.contaIdContaDestino = contaIdContaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransferencia != null ? idTransferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.idTransferencia == null && other.idTransferencia != null) || (this.idTransferencia != null && !this.idTransferencia.equals(other.idTransferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Transferencia[ idTransferencia=" + idTransferencia + " ]";
    }

}
