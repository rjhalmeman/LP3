package Entidades;

// @author Radames (usando gerador de código) 15:28:30 23/05/2024
public class Estado {

    private String siglaEstado;
    private String nomeEstado;
    private int Pais_idPais;

    public Estado() {

    }

    public Estado(String siglaEstado, String nomeEstado, int Pais_idPais) {
        this.siglaEstado = siglaEstado;
        this.nomeEstado = nomeEstado;
        this.Pais_idPais = Pais_idPais;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public int getPais_idPais() {
        return Pais_idPais;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public void setPais_idPais(int Pais_idPais) {
        this.Pais_idPais = Pais_idPais;
    }

    public String toString() {
        return this.siglaEstado + ";" + this.nomeEstado + ";" + this.Pais_idPais;
    }
}
