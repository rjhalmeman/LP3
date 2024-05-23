package DAOs;

import Entidades.Pessoa;
import java.util.List;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    public DAOPessoa() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOPessoa daoPessoa = new DAOPessoa();
        String sql = "SELECT * FROM Pessoa where cpfPessoa = '222' ORDER BY nomePessoa";
        
        List<String> lp = daoPessoa.executarSQL(sql);
        if (lp!=null) {
            return lp;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<String> pessoas = daoPessoa.listarEmOrdemDeNome();
        for (String pessoa : pessoas) {
            System.out.println(pessoa);
        }

        // Fetching a Pessoa entity by its CPF
//        String cpf = "222"; // Example CPF
//        Pessoa pessoa = daoPessoa.obter(cpf, "cpfPessoa");
//
//        if (pessoa
//                != null) {
//            System.out.println("Pessoa found: " + pessoa);
//        } else {
//            System.out.println("Pessoa not found with CPF: " + cpf);
//        }
    }
}
