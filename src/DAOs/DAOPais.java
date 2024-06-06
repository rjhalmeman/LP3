package DAOs;

import Entidades.Pais;
import java.util.List;

public class DAOPais extends DAOGenerico<Pais> {

    public DAOPais() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOPais daoPais = new DAOPais();
        String sql = "SELECT * FROM Pais ORDER BY nomePais";

        List<String> lp = daoPais.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOPais daoPais = new DAOPais();
        List<String> listaPais = daoPais.listarEmOrdemDeNome();
        for (String pais : listaPais) {
            System.out.println(pais);
        }
    }
}
