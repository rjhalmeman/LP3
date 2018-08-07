package DAOs;

import Entidades.PrecoProdutoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProdutoPK extends DAOGenerico<PrecoProdutoPK> {

    private List<PrecoProdutoPK> lista = new ArrayList<>();

    public DAOPrecoProdutoPK() {
        super(PrecoProdutoPK.class);
    }

    public int autoProdutoIdProduto() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.produtoIdProduto) FROM PrecoProdutoPK e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PrecoProdutoPK> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoProdutoPK e WHERE e.produtoIdProduto LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoProdutoPK> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoProdutoPK + e WHERE e.dataPrecoProduto= :id").setParameter("id", id).getResultList();
    }

    public List<PrecoProdutoPK> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProdutoPK e ORDER BY e.dataPrecoProduto").getResultList();
    }

    public List<PrecoProdutoPK> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoProdutoPK e ORDER BY e.produtoIdProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PrecoProdutoPK> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getProdutoIdProduto() + "-" + lf.get(i).getDataPrecoProduto());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPrecoProdutoPK daoPrecoProdutoPK = new DAOPrecoProdutoPK();
        List<PrecoProdutoPK> listaPrecoProdutoPK = daoPrecoProdutoPK.list();
        for (PrecoProdutoPK precoProdutoPK : listaPrecoProdutoPK) {
            System.out.println(precoProdutoPK.getProdutoIdProduto() + "-" + precoProdutoPK.getDataPrecoProduto());
        }
    }
}
