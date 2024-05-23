package Entidades;

// @author Radames (usando gerador de código) 15:30:01 23/05/2024
public class UnidadeDeMedida {private String siglaUnidadeDeMedida;private String nomeUnidadeDeMedida;
public UnidadeDeMedida(){

}public UnidadeDeMedida(String siglaUnidadeDeMedida,String nomeUnidadeDeMedida){this.siglaUnidadeDeMedida = siglaUnidadeDeMedida;this.nomeUnidadeDeMedida = nomeUnidadeDeMedida;}

public String getSiglaUnidadeDeMedida(){return siglaUnidadeDeMedida;
}
public String getNomeUnidadeDeMedida(){return nomeUnidadeDeMedida;
}
public void setSiglaUnidadeDeMedida(String siglaUnidadeDeMedida){this.siglaUnidadeDeMedida = siglaUnidadeDeMedida;
}
public void setNomeUnidadeDeMedida(String nomeUnidadeDeMedida){this.nomeUnidadeDeMedida = nomeUnidadeDeMedida;
}    public String toString() {return this.siglaUnidadeDeMedida+";"+this.nomeUnidadeDeMedida;}}