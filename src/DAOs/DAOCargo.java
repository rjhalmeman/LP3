package DAOs;

import Entidades.Cargo;
import java.util.List;

public class DAOCargo extends DAOGenerico<Cargo> {

    public DAOCargo() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOCargo daoCargo = new DAOCargo();
        String sql = "SELECT * FROM Cargo";

        List<String> lp = daoCargo.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOCargo daoCargo = new DAOCargo();
        List<String> cargos = daoCargo.listarEmOrdemDeNome();
        for (String pais : cargos) {
            System.out.println(pais);
        }
    }
}
