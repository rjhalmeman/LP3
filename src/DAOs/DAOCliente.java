package DAOs;

import Entidades.Pessoa;
import java.util.List;

public class DAOCliente extends DAOGenerico<Pessoa> {

    public DAOCliente() {
        super();
    }

    public List<String> listarClientesComNome() {
        DAOCliente dao = new DAOCliente();
        String sql = "SELECT * from Cliente";
 //  String sql = "SELECT PessoaCpfPessoa, nomePessoa, rendaCliente, dataDeCadastroCliente\n" +
//  "FROM LojaBasica.ClienteComNome;";

        List<String> lp = dao.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOCliente daoPessoa = new DAOCliente();
        List<String> listaPessoa = daoPessoa.listarClientesComNome();
        for (String pessoa : listaPessoa) {
            System.out.println(pessoa);
        }
    }
}
