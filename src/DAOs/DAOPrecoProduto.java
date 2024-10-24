package DAOs;

import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

    private final List<PrecoProduto> lista = new ArrayList<>();
    private Connection connection;

    public DAOPrecoProduto() {
        super(PrecoProduto.class);
        this.connection = connection;
    }

    //esse método foi criado para que a pesquisa pudesse ser feita em uma chave primária composta por 2 atributos
    public PrecoProduto obter(PrecoProdutoPK precoProdutoPK) {
        return em.find(PrecoProduto.class, precoProdutoPK);
    }

    //método criado para testes
    public PrecoProduto obterForced(PrecoProdutoPK precoProdutoPK) {
        // DAOPrecoProduto.em.clear();

        PrecoProduto pp = null;
        int idProd = precoProdutoPK.getProdutoIdProduto();
        Date dataPrecoProduto = precoProdutoPK.getDataPreco();
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
            ls.add(lf.get(i).getPreco() + "-" + lf.get(i).getProduto());
        }
        return ls;
    }
//    public double buscarPrecoMaisRecente(int idProduto) throws SQLException {
//        String sql = "SELECT preco FROM PrecoProduto WHERE idProduto = ? ORDER BY dataPreco DESC LIMIT 1";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, idProduto);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getDouble("preco");
//                }
//            }
//        }
//
//        // Retorne um valor padrão ou trate a ausência de preço mais recente
//        return 0.0; // Valor padrão
//    }

    public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();

        PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK(2, new Date());
        PrecoProduto pp = daoPrecoProduto.obterForced(precoProdutoPK);
        if (pp != null) {
            System.out.println("achou===> " + pp.getProduto().getNomeProduto() + " - "
                    + pp.getPrecoProdutoPK().getDataPreco());
        } else {
            System.out.println("nao achou");
        }

//        List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.list();
//        for (PrecoProduto precoProduto : listaPrecoProduto) {
//            System.out.println(precoProduto.getPrecoProduto() + "-" + precoProduto.getProduto());
//        }
    }
}
