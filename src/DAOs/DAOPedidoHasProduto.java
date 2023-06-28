package DAOs;

import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOPedidoHasProduto extends DAOGenerico<PedidoHasProduto> {

    private final List<PedidoHasProduto> lista = new ArrayList<>();

    public DAOPedidoHasProduto() {
        super(PedidoHasProduto.class);
    }

    public PedidoHasProduto obter(PedidoHasProdutoPK pedidoHasProdutoPK) {
        return em.find(PedidoHasProduto.class, pedidoHasProdutoPK);
    }

    public int autoPedidoIdPedido() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pedidoIdPedido) FROM PedidoHasProduto e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PedidoHasProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PedidoHasProduto e WHERE e.pedidoIdPedido LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PedidoHasProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PedidoHasProduto + e WHERE e.produtoIdProduto= :id").setParameter("id", id).getResultList();
    }

    public List<PedidoHasProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PedidoHasProduto e ORDER BY e.produto.nomeProduto").getResultList();
    }

    public List<PedidoHasProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PedidoHasProduto e ORDER BY e.produtoIdProduto").getResultList();
    }

    public List<PedidoHasProduto> listPedidosPorId(int id) {
        return em.createQuery("SELECT e FROM PedidoHasProduto e WHERE e.pedidoHasProdutoPK.pedidoIdPedido= :id").setParameter("id", id).getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PedidoHasProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getQuantidadeProdutoPedido() + "-" + lf.get(i).getDescontoUnitario());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        List<PedidoHasProduto> listaPedidoHasProduto = daoPedidoHasProduto.listPedidosPorId(2); 
        for (PedidoHasProduto pedidoHasProduto : listaPedidoHasProduto) {
            System.out.println(""
                    +pedidoHasProduto.getPedido().getIdPedido() + "-"
                    + pedidoHasProduto.getProduto().getIdProduto()+" - "
                    + pedidoHasProduto.getProduto().getNomeProduto()+" - "
                    + pedidoHasProduto.getPrecoUnitario()
            );
        }
    }
}
