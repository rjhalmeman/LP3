/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByCpfPessoa", query = "SELECT p FROM Pessoa p WHERE p.cpfPessoa = :cpfPessoa"),
    @NamedQuery(name = "Pessoa.findByNomePessoa", query = "SELECT p FROM Pessoa p WHERE p.nomePessoa = :nomePessoa"),
    @NamedQuery(name = "Pessoa.findByEMailPessoa", query = "SELECT p FROM Pessoa p WHERE p.eMailPessoa = :eMailPessoa")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf_pessoa")
    private String cpfPessoa;
    @Basic(optional = false)
    @Column(name = "nome_pessoa")
    private String nomePessoa;
    @Lob
    @Column(name = "foto_pessoa")
    private String fotoPessoa;
    @Column(name = "e_mail_pessoa")
    private String eMailPessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Cliente> clienteList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Funcionario funcionario;

    public Pessoa() {
    }

    public Pessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public Pessoa(String cpfPessoa, String nomePessoa) {
        this.cpfPessoa = cpfPessoa;
        this.nomePessoa = nomePessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getFotoPessoa() {
        return fotoPessoa;
    }

    public void setFotoPessoa(String fotoPessoa) {
        this.fotoPessoa = fotoPessoa;
    }

    public String getEMailPessoa() {
        return eMailPessoa;
    }

    public void setEMailPessoa(String eMailPessoa) {
        this.eMailPessoa = eMailPessoa;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.cpfPessoa == null && other.cpfPessoa != null) || (this.cpfPessoa != null && !this.cpfPessoa.equals(other.cpfPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Pessoa[ cpfPessoa=" + cpfPessoa + " ]";
    }
    
}
