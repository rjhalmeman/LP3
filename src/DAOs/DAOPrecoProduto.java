package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

    private final List<PrecoProduto> lista = new ArrayList<>();

    public DAOPrecoProduto() {
        super(PrecoProduto.class);
    }

    //esse método foi criado para que a pesquisa pudesse ser feita em uma chave primária composta por 2 atributos
    public PrecoProduto obter(PrecoProdutoPK precoProdutoPK) {
        return em.find(PrecoProduto.class, precoProdutoPK);
    }

    public PrecoProduto obterForced(PrecoProdutoPK precoProdutoPK) {
        // DAOPrecoProduto.em.clear();
        
        PrecoProduto pp = null;
        int idProd = precoProdutoPK.getProdutoIdProduto();
        Date dataPrecoProduto = precoProdutoPK.getDataPrecoProduto();
        try {
            pp = (PrecoProduto) em.createQuery("SELECT e FROM PrecoProduto e WHERE e.precoProdutoPK.produtoIdProduto= :id AND e.precoProdutoPK.dataPrecoProduto =:dt").
                    setParameter("id", idProd).
                    setParameter("dt", dataPrecoProduto).
                    getSingleResult();
        } catch (Exception e) {
            pp = null;
        }
        return pp;
    }

    public List<PrecoProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.precoProduto LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoProduto + e WHERE e.produto= :id").setParameter("id", id).getResultList();
    }

    public List<PrecoProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.produto").getResultList();
    }

    public List<PrecoProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.precoProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PrecoProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPrecoProduto() + "-" + lf.get(i).getProduto());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();

        PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK(2, new Date());
        PrecoProduto pp = daoPrecoProduto.obterForced(precoProdutoPK);
        if (pp != null) {
            System.out.println("achou===> " + pp.getProduto().getNomeProduto() + " - "
                    + pp.getPrecoProdutoPK().getDataPrecoProdutoFormatado());
        } else {
            System.out.println("nao achou");
        }

//        List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.list();
//        for (PrecoProduto precoProduto : listaPrecoProduto) {
//            System.out.println(precoProduto.getPrecoProduto() + "-" + precoProduto.getProduto());
//        }
    }
}
