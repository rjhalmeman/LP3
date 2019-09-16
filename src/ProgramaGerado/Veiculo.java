package ProgramaGerado;// @author Radames

public class Veiculo {

    private String Placa;
    private String Marca;
    private String Modelo;
    private int Ano;

    public Veiculo() {

    }

    public Veiculo(String Placa, String Marca, String Modelo, int Ano) {
        this.Placa = Placa;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Ano = Ano;
    }

    public String getPlaca() {
        return Placa;
    }

    public String getMarca() {
        return Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public int getAno() {
        return Ano;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public String toStringCSV() {
        return this.Placa + ";" + this.Marca + ";" + this.Modelo + ";" + this.Ano;
    }
}
