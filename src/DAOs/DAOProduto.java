package DAOs;

import Entidades.Produto;
import java.util.List;

public class DAOProduto extends DAOGenerico<Produto> {

    public DAOProduto() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOProduto daoProduto = new DAOProduto();
        String sql = "SELECT * FROM Produto ORDER BY nomeProduto";

        List<String> lp = daoProduto.listarComoStrings();
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        DAOProduto daoProduto = new DAOProduto();
        List<String> listaProduto = daoProduto.listarEmOrdemDeNome();
        for (String produto : listaProduto) {
            System.out.println(produto);
        }

        Produto produto = new Produto();
        produto.setIdProduto(22);
        produto.setNomeProduto("novo nome");
        produto.setQuantidadeEmEstoque(100);
        produto.setUnidadeDeMedidaSiglaUnidadeDeMedida("kg");

        String status = daoProduto.atualizar(produto, "idProduto", produto.getIdProduto());

        System.out.println("status " + status);
        //String sql = "UPDATE Produto SET nomeProduto = 'asdfasdfasdf', quantidadeEmEstoque = '222', UnidadeDeMedidaSiglaUnidadeDeMedida = 'dddd' WHERE idProduto = 22";

    }
}
