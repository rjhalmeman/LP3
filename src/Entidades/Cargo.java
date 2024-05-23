package Entidades;

// @author Radames (usando gerador de código) 15:30:12 23/05/2024
public class Cargo {private int idCargo;private String nomeCargo;
public Cargo(){

}public Cargo(int idCargo,String nomeCargo){this.idCargo = idCargo;this.nomeCargo = nomeCargo;}

public int getIdCargo(){return idCargo;
}
public String getNomeCargo(){return nomeCargo;
}
public void setIdCargo(int idCargo){this.idCargo = idCargo;
}
public void setNomeCargo(String nomeCargo){this.nomeCargo = nomeCargo;
}    public String toString() {return this.idCargo+";"+this.nomeCargo;}}