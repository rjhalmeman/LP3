package DAOs;

import Entidades.Cidade;
import java.util.List;

public class DAOCidade extends DAOGenerico<Cidade> {

    public DAOCidade() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOCidade daoCidade = new DAOCidade();
        String sql = "SELECT * FROM Cidade ORDER BY nomeCidade";

        List<String> lp = daoCidade.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOCidade daoCidade = new DAOCidade();
        List<String> listaCidade = daoCidade.listarEmOrdemDeNome();
        for (String cidade : listaCidade) {
            System.out.println(cidade);
        }
    }
}
