package Entidades;

// @author Radames (usando gerador de código) 15:30:26 23/05/2024
public class PedidoHasProduto {private int quantidade;private double preco_unitario_produto;private int produto_idProduto;private int Pedido_idPedido;
public PedidoHasProduto(){

}public PedidoHasProduto(int quantidade,double preco_unitario_produto,int produto_idProduto,int Pedido_idPedido){this.quantidade = quantidade;this.preco_unitario_produto = preco_unitario_produto;this.produto_idProduto = produto_idProduto;this.Pedido_idPedido = Pedido_idPedido;}

public int getQuantidade(){return quantidade;
}
public double getPreco_unitario_produto(){return preco_unitario_produto;
}
public int getProduto_idProduto(){return produto_idProduto;
}
public int getPedido_idPedido(){return Pedido_idPedido;
}
public void setQuantidade(int quantidade){this.quantidade = quantidade;
}
public void setPreco_unitario_produto(double preco_unitario_produto){this.preco_unitario_produto = preco_unitario_produto;
}
public void setProduto_idProduto(int produto_idProduto){this.produto_idProduto = produto_idProduto;
}
public void setPedido_idPedido(int Pedido_idPedido){this.Pedido_idPedido = Pedido_idPedido;
}    public String toString() {return this.quantidade+";"+this.preco_unitario_produto+";"+this.produto_idProduto+";"+this.Pedido_idPedido;}}