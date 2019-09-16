package ProgramaGerado;// @author Radames

public class Cidade {

    private int codigo;
    private String nome;
    private String estado;
    private String cep;

    public Cidade() {

    }

    public Cidade(int codigo, String nome, String estado, String cep) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
        this.cep = cep;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String toStringCSV() {
        return this.codigo + ";" + this.nome + ";" + this.estado + ";" + this.cep;
    }
}
