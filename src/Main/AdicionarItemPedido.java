package Main;

import DAOs.DAOItensPedido;
import DAOs.DAOPedido;
import DAOs.DAOProduto;
import Entidades.ItensPedido;
import Entidades.ItensPedidoPK;
import Entidades.Pedido;
import Entidades.Produto;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import myUtil.StringTools;

/**
 * @author Radames J Halmeman - rjhalmeman@gmail.com
 */
public class AdicionarItemPedido {

    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        DAOPedido daoPedido = new DAOPedido();

        Produto produto = new Produto();
        DAOProduto daoProduto = new DAOProduto();

        ItensPedido itensPedido = new ItensPedido();
        ItensPedidoPK itensPedidoPK = new ItensPedidoPK();

        DAOItensPedido daoItensPedido = new DAOItensPedido();

        /*
        O quero fazer? Adicionar 2 produtos em 1 pedido.
         */
        //localizar o pedido
        pedido = daoPedido.obter(1);
        if (pedido != null) {
            System.out.println("Pedido: " + pedido.getIdPedido()
                    + "\nCliente:" + pedido.getClienteIdCliente().getNomeCliente()
                    + "\nData:" + new SimpleDateFormat("dd/MM/yyyy").format(pedido.getDataPedido())
            );
        }

        double valorEtiqueta = 0;
        //obter o primeiro produto
        produto = daoProduto.obter(1);
        if (produto != null) {
            System.out.println("Produto: " + produto.getIdProduto()
                    + "\nNomeProduto:" + produto.getNomeProduto()
                    + "\nQuantidade em Estoque:" + produto.getQuantidadeNoEstoque()
                    + "\nValor de Etiqueta:" + produto.getPrecoProdutoList().get(produto.getPrecoProdutoList().size() - 1).getPrecoUnitarioProduto()//ultimo preço cadastrado
            );
            valorEtiqueta = produto.getPrecoProdutoList().get(produto.getPrecoProdutoList().size() - 1).getPrecoUnitarioProduto();
        }

        //tenho o pedido e tenho o produto
        itensPedidoPK = new ItensPedidoPK(pedido.getIdPedido(), produto.getIdProduto());
        itensPedido = daoItensPedido.obter(itensPedidoPK);//procura se o item do pedido já foi adicionado anteriormente

        double valorUnitario = 3.5;
        int quantidade = 5;
        itensPedido = new ItensPedido(itensPedidoPK, quantidade, valorUnitario);
        itensPedido.setDesconto(valorEtiqueta - valorUnitario);

        if (itensPedido == null) {//não está na tabela de itens
            daoItensPedido.inserir(itensPedido);
        } else { // já está na tabela, então atualizar
            daoItensPedido.atualizar(itensPedido);
        }

        //repetir o processo para o próximo produto
        valorEtiqueta = 0;
        //obter o segundo produto
        produto = daoProduto.obter(2);
        if (produto != null) {
            System.out.println("Produto: " + produto.getIdProduto()
                    + "\nNomeProduto:" + produto.getNomeProduto()
                    + "\nQuantidade em Estoque:" + produto.getQuantidadeNoEstoque()
                    + "\nValor de Etiqueta:" + produto.getPrecoProdutoList().get(produto.getPrecoProdutoList().size() - 1).getPrecoUnitarioProduto()//ultimo preço cadastrado
            );
            valorEtiqueta = produto.getPrecoProdutoList().get(produto.getPrecoProdutoList().size() - 1).getPrecoUnitarioProduto();
        }

        //tenho o pedido e tenho o produto
        itensPedidoPK = new ItensPedidoPK(pedido.getIdPedido(), produto.getIdProduto());
        itensPedido = daoItensPedido.obter(itensPedidoPK);//procura se o item do pedido já foi adicionado anteriormente

        valorUnitario = 3.99;
        quantidade = 3;
        itensPedido = new ItensPedido(itensPedidoPK, quantidade, valorUnitario);
        itensPedido.setDesconto(valorEtiqueta - valorUnitario);

        if (itensPedido == null) {//não está na tabela de itens
            daoItensPedido.inserir(itensPedido);
        } else { // já está na tabela, então atualizar
            daoItensPedido.atualizar(itensPedido);
        }

        //Listar o pedido e calcular o total
        pedido = daoPedido.obter(1);//obter o pedido já atualizado com os itens

        System.out.println("----------------------------------\n\n"
                + "Pedido: " + pedido.getIdPedido()
                + "\nCliente:" + pedido.getClienteIdCliente().getNomeCliente()
                + "\nData:" + new SimpleDateFormat("dd/MM/yyyy").format(pedido.getDataPedido())
        );
        int idPedido = pedido.getIdPedido();
        StringTools st = new StringTools();
        System.out.println("Itens");
        System.out.print(st.centralizaString("idPedido", 15));
        System.out.print(st.centralizaString("idProduto", 15));
        System.out.print(st.ajustaLargura("nomeProduto", 40));
        System.out.print(st.centralizaString("Quant", 10));
        System.out.print(st.centralizaString("valorUni", 10));
        System.out.print(st.centralizaString("desconto", 10));
        System.out.println(st.alinhaDireita("subtotal", 10));

        List<ItensPedido> listaItens = daoItensPedido.listByIdPedido(idPedido);
        double total = 0;
        for (ItensPedido item : listaItens) {
            double subTotal = item.getQuantidade() * item.getValorUnitario();
            System.out.print(st.centralizaString(String.valueOf(item.getItensPedidoPK().getPedidoIdPedido()), 15));
            System.out.print(st.centralizaString(String.valueOf(item.getItensPedidoPK().getProdutoIdProduto()), 15));
            System.out.print(st.ajustaLargura(daoProduto.obter(item.getItensPedidoPK().getProdutoIdProduto()).getNomeProduto(), 40));
            System.out.print(st.centralizaString(String.valueOf(item.getQuantidade()), 10));
            System.out.print(st.centralizaString(String.valueOf(new DecimalFormat("###,###,##0.00").format(item.getValorUnitario())), 10));
            System.out.print(st.centralizaString(new DecimalFormat("###,###,##0.00").format(item.getDesconto()), 10));
            System.out.println(st.alinhaDireita(String.valueOf(new DecimalFormat("###,###,##0.00").format(subTotal)), 10));
            total += subTotal;
        }
        System.out.println(st.alinhaDireita("-----------", 110));
        System.out.println(st.alinhaDireita("Total " + new DecimalFormat("###,###,##0.00").format(total), 110));

    }

}
