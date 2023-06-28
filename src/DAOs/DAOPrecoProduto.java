package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

    public DAOPrecoProduto() {
        super(PrecoProduto.class);
    }

    public PrecoProduto obter(PrecoProdutoPK precoProdutoPk) {
        return em.find(PrecoProduto.class, precoProdutoPk);
    }

    public List<PrecoProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.precoProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings() {
        List<PrecoProduto> lf = listInOrderNome();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPrecoProdutoPK().toString());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();

        PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();
        PrecoProduto precoProduto = new PrecoProduto();

        precoProdutoPK.setProdutoIdProduto(2);
        precoProdutoPK.setDataPrecoProduto(new Date());

        precoProduto.setPrecoProduto(8.00);
        precoProduto.setPrecoProdutoPK(precoProdutoPK);
        daoPrecoProduto.inserir(precoProduto);

    }
}
