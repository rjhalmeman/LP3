package Entidades;

// @author Radames (usando gerador de código) 15:28:45 23/05/2024
public class Cidade {private int idCidade;private String nomeCidade;private String siglaEstado;
public Cidade(){

}public Cidade(int idCidade,String nomeCidade,String siglaEstado){this.idCidade = idCidade;this.nomeCidade = nomeCidade;this.siglaEstado = siglaEstado;}

public int getIdCidade(){return idCidade;
}
public String getNomeCidade(){return nomeCidade;
}
public String getSiglaEstado(){return siglaEstado;
}
public void setIdCidade(int idCidade){this.idCidade = idCidade;
}
public void setNomeCidade(String nomeCidade){this.nomeCidade = nomeCidade;
}
public void setSiglaEstado(String siglaEstado){this.siglaEstado = siglaEstado;
}    public String toString() {return this.idCidade+";"+this.nomeCidade+";"+this.siglaEstado;}}