package ProgramaGerado;// @author Radames

public class Joia {

    private int idJoia;
    private String nomeJoia;
    private double precoJoia;

    public Joia() {

    }

    public Joia(int idJoia, String nomeJoia, double precoJoia) {
        this.idJoia = idJoia;
        this.nomeJoia = nomeJoia;
        this.precoJoia = precoJoia;
    }

    public int getIdJoia() {
        return idJoia;
    }

    public String getNomeJoia() {
        return nomeJoia;
    }

    public double getPrecoJoia() {
        return precoJoia;
    }

    public void setIdJoia(int idJoia) {
        this.idJoia = idJoia;
    }

    public void setNomeJoia(String nomeJoia) {
        this.nomeJoia = nomeJoia;
    }

    public void setPrecoJoia(double precoJoia) {
        this.precoJoia = precoJoia;
    }

    public String toStringCSV() {
        return this.idJoia + ";" + this.nomeJoia + ";" + this.precoJoia;
    }
}
