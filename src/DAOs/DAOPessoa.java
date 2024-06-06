package DAOs;

import Entidades.Pessoa;
import java.util.List;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    public DAOPessoa() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOPessoa daoPessoa = new DAOPessoa();
        String sql = "SELECT * FROM Pessoa ORDER BY nomePessoa";

        List<String> lp = daoPessoa.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<String> listaPessoa = daoPessoa.listarEmOrdemDeNome();
        for (String pessoa : listaPessoa) {
            System.out.println(pessoa);
        }
    }
}
