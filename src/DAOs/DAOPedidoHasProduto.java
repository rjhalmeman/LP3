package DAOs;

import Entidades.PedidoHasProduto;
import java.util.List;

public class DAOPedidoHasProduto extends DAOGenerico<PedidoHasProduto> {

    public DAOPedidoHasProduto() {
        super();
    }

    public List<String> listarEmOrdemDePedidoProduto(String idPedido) {
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();

        String sql = "SELECT * FROM LojaBasica.PedidoHasProduto php WHERE php.PedidoIdPedido = " + idPedido + " ORDER BY php.PedidoIdPedido , php.ProdutoIdProduto";
//        String sql = "SELECT * FROM PedidoHasProduto ORDER BY idPedido, idProduto";

        List<String> lp = daoPedidoHasProduto.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public List<String> essePedidoHasProduto(String idPedido, String idProduto) {
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        //   System.out.println("está no essePedidoHasProduto");
        String sql = "SELECT * "
                + "FROM LojaBasica.PedidoHasProduto php "
                + "WHERE php.PedidoIdPedido = " + idPedido + " AND "
                + " php.ProdutoIdProduto = " + idProduto;

        List<String> lp = daoPedidoHasProduto.executarSQL(sql);

        if (!lp.isEmpty()) {
            return lp;
        } else {
            return null;
        }
    }

    public void atualizarPHP(PedidoHasProduto phpAux) {
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        String sql = "UPDATE LojaBasica.PedidoHasProduto SET "
                + "quantidade=" + phpAux.getQuantidade() + ", "
                + "precoUnitarioProduto=" + phpAux.getPrecoUnitarioProduto() + " "
                + "WHERE ProdutoIdProduto=" + phpAux.getProdutoIdProduto()
                + " AND PedidoIdPedido=" + phpAux.getPedidoIdPedido() + ";";
        
        daoPedidoHasProduto.executarUpdate(sql);
    }

    public static void main(String[] args) {
        DAOPedidoHasProduto daoPedido = new DAOPedidoHasProduto();
        List<String> listaPedido = daoPedido.listarEmOrdemDePedidoProduto("1");
        for (String pedido : listaPedido) {
            System.out.println(pedido);
        }
    }

}
