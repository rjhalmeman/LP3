package Entidades;

// @author Radames (usando gerador de código) 15:29:22 23/05/2024
public class Funcionario {private String pessoa_cpfPessoa;private double salario;private int Cargos_idCargo;
public Funcionario(){

}public Funcionario(String pessoa_cpfPessoa,double salario,int Cargos_idCargo){this.pessoa_cpfPessoa = pessoa_cpfPessoa;this.salario = salario;this.Cargos_idCargo = Cargos_idCargo;}

public String getPessoa_cpfPessoa(){return pessoa_cpfPessoa;
}
public double getSalario(){return salario;
}
public int getCargos_idCargo(){return Cargos_idCargo;
}
public void setPessoa_cpfPessoa(String pessoa_cpfPessoa){this.pessoa_cpfPessoa = pessoa_cpfPessoa;
}
public void setSalario(double salario){this.salario = salario;
}
public void setCargos_idCargo(int Cargos_idCargo){this.Cargos_idCargo = Cargos_idCargo;
}    public String toString() {return this.pessoa_cpfPessoa+";"+this.salario+";"+this.Cargos_idCargo;}}