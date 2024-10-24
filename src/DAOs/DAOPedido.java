package DAOs;

import Entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author belly
 */
public class DAOPedido extends DAOGenerico<Pedido> {

    private List<Pedido> lista = new ArrayList<>();

    public DAOPedido() {
        super(Pedido.class);
    }

    public int autoIdItensCardapio() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPedido) FROM + nomeDaClasse +  e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Pedido> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Pedido e WHERE e.idPedido) LIKE :datahoraPedido").setParameter("datahoraPedido", "%" + nome + "%").getResultList();
    }

    public List<Pedido> listById(int id) {
        return em.createQuery("SELECT e FROM Pedido + e WHERE e.idPedido= :id").setParameter("id", id).getResultList();
    }

    public List<Pedido> listInOrderNome() {
        return em.createQuery("SELECT e FROM Pedido e ORDER BY e.datahoraPedido").getResultList();
    }

    public List<Pedido> listInOrderId() {
        return em.createQuery("SELECT e FROM Pedido e ORDER BY e.idPedido").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Pedido> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdPedido() + "-" + lf.get(i).getDatahoraPedido());
        }
        return ls;
    }

    public String[] listInOrderNomeStringsArray(String categoria) {
        List< Pedido> lf = em.createQuery("SELECT e FROM Pedido e WHERE e.idPedido :id").setParameter("id", categoria).getResultList();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i] = (lf.get(i).getIdPedido() + "-" + lf.get(i).getDatahoraPedido());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPedido daoPedido = new DAOPedido();
        List<Pedido> listaPedido = daoPedido.list();
        for (Pedido pedido : listaPedido) {
            System.out.println(pedido.getIdPedido() + "-" + pedido.getDatahoraPedido());
        }
    }
}
