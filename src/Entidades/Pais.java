package Entidades;

// @author Radames (usando gerador de código) 15:18:06 23/05/2024
public class Pais {

    private int idPais;
    private String nomePais;
    private String siglaPais;

    public Pais() {

    }

    public Pais(int idPais, String nomePais, String siglaPais) {
        this.idPais = idPais;
        this.nomePais = nomePais;
        this.siglaPais = siglaPais;
    }

    public int getIdPais() {
        return idPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public void setSiglaPais(String siglaPais) {
        this.siglaPais = siglaPais;
    }

    public String toString() {
        return this.idPais + ";" + this.nomePais + ";" + this.siglaPais;
    }
}
