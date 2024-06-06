package DAOs;

import Entidades.UnidadeDeMedida;
import java.util.ArrayList;
import java.util.List;

public class DAOUnidadeDeMedida extends DAOGenerico<UnidadeDeMedida> {

    public DAOUnidadeDeMedida() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
        String sql = "SELECT * FROM UnidadeDeMedida";

        List<String> lp = daoUnidadeDeMedida.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }

    }

    public List<String> listaParaCombobox() {
        DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
        String sql = "SELECT * FROM UnidadeDeMedida ORDER BY siglaUnidadeDeMedida";

        List<String> lp = daoUnidadeDeMedida.executarSQL(sql);

        if (lp != null) {
            List<String> lc = new ArrayList<>();
            for (String string : lp) {
                String s[] = string.split(",");
                String id = s[0].split("=")[1];
                String nome = s[1].split("=")[1];
                lc.add(id + "-" + nome);
            }
            return lc;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        DAOUnidadeDeMedida daoUnidadeDeMedida = new DAOUnidadeDeMedida();
        List<String> listaUnidadesDeMedidas = daoUnidadeDeMedida.listaParaCombobox();
        for (String unidadeDeMedida : listaUnidadesDeMedidas) {
            System.out.println(unidadeDeMedida);
        }

        List<UnidadeDeMedida> lum = new ArrayList<>();
        lum.add(new UnidadeDeMedida("kg", "Quilograma"));
        lum.add(new UnidadeDeMedida("l", "Litro"));
        lum.add(new UnidadeDeMedida("un", "Unidade"));

        for (UnidadeDeMedida unidadeDeMedida : lum) {
//            daoUnidadeDeMedida.inserir(unidadeDeMedida);
        }

    }
}
