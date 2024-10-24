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
import tools.CaixaDeFerramentas;

/**
 *
 * @author belly
 */
@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_cpf_pessoa")
    private String pessoaCpfPessoa;
    @Basic(optional = false)
    @Column(name = "nome_pessoa")
    private String nomePessoa;
    @Basic(optional = false)
    @Column(name = "receber_notificacao")
    private short receberNotificacao;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    public Clientes() {
    }

    public String getPessoaCpfPessoa() {
        return pessoaCpfPessoa;
    }

    public void setPessoaCpfPessoa(String pessoaCpfPessoa) {
        this.pessoaCpfPessoa = pessoaCpfPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public short getReceberNotificacao() {
        return receberNotificacao;
    }

    public void setReceberNotificacao(short receberNotificacao) {
        this.receberNotificacao = receberNotificacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return pessoaCpfPessoa + ";" + nomePessoa + ";" + receberNotificacao + ";" + cf.converteDeDateParaString(dataCadastro);
    }

}
