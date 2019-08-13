package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import Entidades.PrecoProduto;
import java.util.ArrayList;
import java.util.List;

public class DAOPedidoHasProduto extends DAOGenerico<PedidoHasProduto> {

private final List<PedidoHasProduto> lista = new ArrayList<>();    public DAOPedidoHasProduto(){
        super(PedidoHasProduto.class);
    }

     //esse método foi criado para que a pesquisa pudesse ser feita em uma chave primária composta por 2 atributos
    public PedidoHasProduto obter(PedidoHasProdutoPK pedidoHasProdutoPK) {
        return em.find(PedidoHasProduto.class, pedidoHasProdutoPK);
    }


    public int autoQuantidadeProdutoPedido() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.quantidadeProdutoPedido) FROM PedidoHasProduto e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PedidoHasProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PedidoHasProduto e WHERE e.quantidadeProdutoPedido LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PedidoHasProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PedidoHasProduto + e WHERE e.precoUnitario= :id").setParameter("id", id).getResultList();
    }

    public List<PedidoHasProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PedidoHasProduto e ORDER BY e.precoUnitario").getResultList();
    }

    public List<PedidoHasProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PedidoHasProduto e ORDER BY e.quantidadeProdutoPedido").getResultList();
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
            ls.add(lf.get(i).getQuantidadeProdutoPedido() + "-" + lf.get(i).getPrecoUnitario());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        List<PedidoHasProduto> listaPedidoHasProduto = daoPedidoHasProduto.list();
        for (PedidoHasProduto pedidoHasProduto : listaPedidoHasProduto) {
            System.out.println(pedidoHasProduto.getQuantidadeProdutoPedido()+"-"+pedidoHasProduto.getPrecoUnitario());
        }
    }}