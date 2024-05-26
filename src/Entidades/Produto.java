package Entidades;

// @author Radames (usando gerador de código) 14:32:15 25/05/2024
public class Produto {

    private int idProduto;
    private String nomeProduto;
    private int quantidadeEmEstoque;
    private String UnidadeDeMedidaSiglaUnidadeDeMedida;

    public Produto() {

    }

    public Produto(int idProduto, String nomeProduto, int quantidadeEmEstoque, String UnidadeDeMedidaSiglaUnidadeDeMedida) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.UnidadeDeMedidaSiglaUnidadeDeMedida = UnidadeDeMedidaSiglaUnidadeDeMedida;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public String getUnidadeDeMedidaSiglaUnidadeDeMedida() {
        return UnidadeDeMedidaSiglaUnidadeDeMedida;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void setUnidadeDeMedidaSiglaUnidadeDeMedida(String UnidadeDeMedidaSiglaUnidadeDeMedida) {
        this.UnidadeDeMedidaSiglaUnidadeDeMedida = UnidadeDeMedidaSiglaUnidadeDeMedida;
    }

    public String toString() {
        return this.idProduto + ";" + this.nomeProduto + ";" + this.quantidadeEmEstoque + ";" + this.UnidadeDeMedidaSiglaUnidadeDeMedida;
    }
}
