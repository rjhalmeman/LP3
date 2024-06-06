package DAOs;

import Entidades.Pedido;
import java.util.List;

public class DAOPedido extends DAOGenerico<Pedido> {

    public DAOPedido() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOPedido daoPedido = new DAOPedido();
        String sql = "SELECT * FROM Pedido ORDER BY dataDoPedido";

        List<String> lp = daoPedido.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOPedido daoPedido = new DAOPedido();
        List<String> listaPedido = daoPedido.listarEmOrdemDeNome();
        for (String pedido : listaPedido) {
            System.out.println(pedido);
        }
    }
}
