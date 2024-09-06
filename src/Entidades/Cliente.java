package Entidades;

// @author Radames (usando gerador de código) 15:29:40 23/05/2024
import java.util.Date;

public class Cliente {

    private double rendaCliente;
    private Date dataDeCadastroCliente;
    private String Pessoa_cpfPessoa;

    public Cliente() {

    }

    public Cliente(double rendaCliente, Date dataDeCadastroCliente, String Pessoa_cpfPessoa) {
        this.rendaCliente = rendaCliente;
        this.dataDeCadastroCliente = dataDeCadastroCliente;
        this.Pessoa_cpfPessoa = Pessoa_cpfPessoa;
    }

    public double getRendaCliente() {
        return rendaCliente;
    }

    public Date getDataDeCadastroCliente() {
        return dataDeCadastroCliente;
    }

    public String getPessoa_cpfPessoa() {
        return Pessoa_cpfPessoa;
    }

    public void setRendaCliente(double rendaCliente) {
        this.rendaCliente = rendaCliente;
    }

    public void setDataDeCadastroCliente(Date dataDeCadastroCliente) {
        this.dataDeCadastroCliente = dataDeCadastroCliente;
    }

    public void setPessoa_cpfPessoa(String Pessoa_cpfPessoa) {
        this.Pessoa_cpfPessoa = Pessoa_cpfPessoa;
    }

    public String toString() {
        return this.rendaCliente + ";" + this.dataDeCadastroCliente + ";" + this.Pessoa_cpfPessoa;
    }
}
