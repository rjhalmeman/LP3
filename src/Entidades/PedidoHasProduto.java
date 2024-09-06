package Entidades;

// @author Radames (usando gerador de código) 14:43:15 05/09/2024
public class PedidoHasProduto {

    private int quantidade;
    private double precoUnitarioProduto;
    private int ProdutoIdProduto;
    private int PedidoIdPedido;

    public PedidoHasProduto() {

    }

    public PedidoHasProduto(int quantidade, double precoUnitarioProduto, int ProdutoIdProduto, int PedidoIdPedido) {
        this.quantidade = quantidade;
        this.precoUnitarioProduto = precoUnitarioProduto;
        this.ProdutoIdProduto = ProdutoIdProduto;
        this.PedidoIdPedido = PedidoIdPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public int getProdutoIdProduto() {
        return ProdutoIdProduto;
    }

    public int getPedidoIdPedido() {
        return PedidoIdPedido;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitarioProduto(double precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }

    public void setProdutoIdProduto(int ProdutoIdProduto) {
        this.ProdutoIdProduto = ProdutoIdProduto;
    }

    public void setPedidoIdPedido(int PedidoIdPedido) {
        this.PedidoIdPedido = PedidoIdPedido;
    }

    public String toString() {
        return this.quantidade + ";" + this.precoUnitarioProduto + ";" + this.ProdutoIdProduto + ";" + this.PedidoIdPedido;
    }
}
