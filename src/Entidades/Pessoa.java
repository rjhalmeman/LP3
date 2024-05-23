package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {

    private String cpfPessoa;
    private String nomePessoa;
    private Date dataNascimentoPessoa;
    private int Endereco_idEndereco;

    public Pessoa() {
    }

    public Pessoa(String cpfPessoa, String nomePessoa, Date dataNascimentoPessoa, int Endereco_idEndereco) {
        this.cpfPessoa = cpfPessoa;
        this.nomePessoa = nomePessoa;
        this.dataNascimentoPessoa = dataNascimentoPessoa;
        this.Endereco_idEndereco = Endereco_idEndereco;
    }

    public String getCpf_pessoa() {
        return cpfPessoa;
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

    public Date getDataNascimentoPessoa() {
        return dataNascimentoPessoa;
    }

    public void setDataNascimentoPessoa(Date dataNascimentoPessoa) {
        this.dataNascimentoPessoa = dataNascimentoPessoa;
    }

    public int getEndereco_idEndereco() {
        return Endereco_idEndereco;
    }

    public void setEndereco_idEndereco(int Endereco_idEndereco) {
        this.Endereco_idEndereco = Endereco_idEndereco;
    }

    @Override
    public String toString() {
        return cpfPessoa + ";" + nomePessoa + ";"
                + new SimpleDateFormat("dd/MM/yyyy").format(dataNascimentoPessoa) + ";" + Endereco_idEndereco;
    }

}
