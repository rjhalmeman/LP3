package DAOs;

import Entidades.PrecoProdutoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProdutoPK extends DAOGenerico<PrecoProdutoPK> {

    private List<PrecoProdutoPK> lista = new ArrayList<>();

    public DAOPrecoProdutoPK() {
        super(PrecoProdutoPK.class);
    }

    public static void main(String[] args) {
        DAOPrecoProdutoPK daoPrecoProdutoPK = new DAOPrecoProdutoPK();
        List<PrecoProdutoPK> listaPrecoProdutoPK = daoPrecoProdutoPK.list();
        for (PrecoProdutoPK precoProdutoPK : listaPrecoProdutoPK) {
            System.out.println(precoProdutoPK.getProdutoIdProduto() + "-" + precoProdutoPK.getDataPrecoProduto());
        }
    }
}
