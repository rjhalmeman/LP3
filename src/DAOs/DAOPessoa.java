package DAOs;

import Conexao.SQLRunner;
import Conexao.UP;
import Entidades.Pessoa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    public DAOPessoa() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOPessoa daoPessoa = new DAOPessoa();
        String sql = "SELECT nomePessoa, cpfPessoa FROM Pessoa ORDER BY nomePessoa";
        
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
