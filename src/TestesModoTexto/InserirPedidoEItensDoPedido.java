package TestesModoTexto;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import DAOs.DAOPedido;
import DAOs.DAOPedidoHasProduto;
import Entidades.Pedido;
import java.util.Date;

/**
 * @author Radames J Halmeman  - rjhalmeman@gmail.com
 */
public class InserirPedidoEItensDoPedido {
    public static void main(String[] args) {
        DAOPedido daoPedido = new DAOPedido();
        Pedido pedido;
        
        pedido = new Pedido(1, new Date());
        if (daoPedido.obter(pedido.getIdPedido())==null) {            
            pedido.setClientePessoaCpfPessoa(new DAOCliente().obter("4"));
            pedido.setFuncionarioPessoaCpfPessoa(new DAOFuncionario().obter("2"));
            daoPedido.inserir(pedido);
        }
       
        pedido = new Pedido(2, new Date());
        if (daoPedido.obter(pedido.getIdPedido())==null) {            
            pedido.setClientePessoaCpfPessoa(new DAOCliente().obter("5"));
            pedido.setFuncionarioPessoaCpfPessoa(new DAOFuncionario().obter("3"));
            daoPedido.inserir(pedido);
        }
        System.out.println("Pedidos inseridos");
        
        //pedido_has_produto
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        
        
        


    }

}
