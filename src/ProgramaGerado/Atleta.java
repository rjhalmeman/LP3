package ProgramaGerado;// @author Radames

public class Atleta {

    private int id;
    private String nome;    

  
    private double altura;
    private String esporte;

      public Atleta() {
    }

    public Atleta(int id, String nome, double altura, String esporte) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.esporte = esporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }
      
      

}
