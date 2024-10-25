package DAOs;


import Entidades.Funcionario;
import java.util.List;

public class DAOFuncionario extends DAOGenerico<Funcionario> {

    public DAOFuncionario() {
        super();
    }

    

    public static void main(String[] args) {
        DAOFuncionario daoPessoa = new DAOFuncionario();
        List<String> listaFunc = daoPessoa.listarComoStrings();
        for (String func : listaFunc) {
            System.out.println(func);
        }
    }
}
