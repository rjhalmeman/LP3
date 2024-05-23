package DAOs;

import Entidades.Estado;
import java.util.List;

public class DAOEstado extends DAOGenerico<Estado> {

    public DAOEstado() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOEstado daoEstado = new DAOEstado();
        String sql = "SELECT * FROM Estado";
        
        List<String> lp = daoEstado.executarSQL(sql);
        if (lp!=null) {
            return lp;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        DAOEstado daoEstado = new DAOEstado();
        List<String> estados = daoEstado.listarEmOrdemDeNome();
        for (String pais : estados) {
            System.out.println(pais);
        }

    }
}
