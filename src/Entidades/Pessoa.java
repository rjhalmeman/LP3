package Entidades;

// @author Radames (usando gerador de código) 12:24:14 06/06/2024
import java.util.Date;

public class Pessoa {

    private String cpfPessoa;
    private String nomePessoa;
    private Date dataNascimentoPessoa;
    private int EnderecoIdEndereco;

    public Pessoa() {

    }

    public Pessoa(String cpfPessoa, String nomePessoa, Date dataNascimentoPessoa, int EnderecoIdEndereco) {
        this.cpfPessoa = cpfPessoa;
        this.nomePessoa = nomePessoa;
        this.dataNascimentoPessoa = dataNascimentoPessoa;
        this.EnderecoIdEndereco = EnderecoIdEndereco;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public Date getDataNascimentoPessoa() {
        return dataNascimentoPessoa;
    }

    public int getEnderecoIdEndereco() {
        return EnderecoIdEndereco;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public void setDataNascimentoPessoa(Date dataNascimentoPessoa) {
        this.dataNascimentoPessoa = dataNascimentoPessoa;
    }

    public void setEnderecoIdEndereco(int EnderecoIdEndereco) {
        this.EnderecoIdEndereco = EnderecoIdEndereco;
    }

    public String toString() {
        return this.cpfPessoa + ";" + this.nomePessoa + ";" + this.dataNascimentoPessoa + ";" + this.EnderecoIdEndereco;
    }
}
