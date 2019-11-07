package Main;

/**
 *
 * @author radames
 */
public class Trabalhador {

    private String cpf;
    private String nome;
    private double salario;
    private boolean aposentado;

    public Trabalhador() {
    }

    public Trabalhador(String cpf, String nome, double salario, boolean aposentado) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.aposentado = aposentado;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAposentado() {
        return aposentado;
    }

    public void setAposentado(boolean aposentado) {
        this.aposentado = aposentado;
    }

    @Override
    public String toString() {
        return cpf + ";" + nome + ";" + salario + ";" + aposentado;
    }

}
