package DAOs;

import Entidades.ItensPedido;
import Entidades.ItensPedidoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOItensPedido extends DAOGenerico<ItensPedido> {

    private List<ItensPedido> lista = new ArrayList<>();

    public DAOItensPedido() {
        super(ItensPedido.class);
    }
    
    //método para obter usando a chave composta
    public ItensPedido obter(ItensPedidoPK itensPedidoPK) {
        return em.find(ItensPedido.class, itensPedidoPK);
    }

    public List<ItensPedido> listByNome(String nome) {
        return em.createQuery("SELECT e FROM ItensPedido e WHERE e.quantidade LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<ItensPedido> listByIdPedido(int id) {
        return em.createQuery("SELECT e FROM ItensPedido e WHERE e.pedido.idPedido =:id").setParameter("id", id).getResultList();
    }

    public List<ItensPedido> listInOrderNome() {
        return em.createQuery("SELECT e FROM ItensPedido e ORDER BY e.valorUnitario").getResultList();
    }

    public List<ItensPedido> listInOrderId() {
        return em.createQuery("SELECT e FROM ItensPedido e ORDER BY e.quantidade").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<ItensPedido> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getQuantidade() + "-" + lf.get(i).getValorUnitario());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOItensPedido daoItensPedido = new DAOItensPedido();
        List<ItensPedido> listaItensPedido = daoItensPedido.list();
        for (ItensPedido itensPedido : listaItensPedido) {
            System.out.println(itensPedido.getQuantidade() + "-" + itensPedido.getValorUnitario());
        }
    }
}
