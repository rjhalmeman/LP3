/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "clientesDaLojinha")
@NamedQueries({
    @NamedQuery(name = "ClientesDaLojinha.findAll", query = "SELECT c FROM ClientesDaLojinha c"),
    @NamedQuery(name = "ClientesDaLojinha.findByCpfPessoa", query = "SELECT c FROM ClientesDaLojinha c WHERE c.cpfPessoa = :cpfPessoa"),
    @NamedQuery(name = "ClientesDaLojinha.findByDataCadastro", query = "SELECT c FROM ClientesDaLojinha c WHERE c.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "ClientesDaLojinha.findByNomePessoa", query = "SELECT c FROM ClientesDaLojinha c WHERE c.nomePessoa = :nomePessoa"),
    @NamedQuery(name = "ClientesDaLojinha.findByRendaCliente", query = "SELECT c FROM ClientesDaLojinha c WHERE c.rendaCliente = :rendaCliente")})
public class ClientesDaLojinha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf_pessoa")
    private String cpfPessoa;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "nome_pessoa")
    private String nomePessoa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "renda_cliente")
    private Double rendaCliente;

    public ClientesDaLojinha() {
    }

    public ClientesDaLojinha(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public Double getRendaCliente() {
        return rendaCliente;
    }

    public void setRendaCliente(Double rendaCliente) {
        this.rendaCliente = rendaCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpfPessoa != null ? cpfPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientesDaLojinha)) {
            return false;
        }
        ClientesDaLojinha other = (ClientesDaLojinha) object;
        if ((this.cpfPessoa == null && other.cpfPessoa != null) || (this.cpfPessoa != null && !this.cpfPessoa.equals(other.cpfPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ClientesDaLojinha[ cpfPessoa=" + cpfPessoa + " ]";
    }
    
}
