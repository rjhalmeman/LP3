package Main;

import DAOs.DAOFornecedor;
import DAOs.DAOProduto;
import Entidades.Fornecedor;
import Entidades.Produto;
import java.util.List;

/**
 * @author Radames J Halmeman - rjhalmeman@gmail.com
 */
public class AdicionarProdutosAUmFornecedor_Exemplo {

    public static void main(String[] args) {
        Fornecedor fornecedor = new Fornecedor();
        DAOFornecedor daoFornecedor = new DAOFornecedor();
        Produto produto = new Produto();
        DAOProduto daoProduto = new DAOProduto();

        //obter um fornecedor
        fornecedor = daoFornecedor.obter(1);
        if (fornecedor != null) {
            List<Produto> lp = fornecedor.getProdutoList();
            produto = daoProduto.obter(3);
            if (lp.indexOf(produto) < 0) {
                lp.add(produto); //adiciona, pois não está na lista
            }
            produto = daoProduto.obter(5);
            if (lp.indexOf(produto) < 0) {
                lp.add(produto); //adiciona, pois não está na lista
            }

            daoFornecedor.atualizar(fornecedor); //atualiza a tabela que possui a entidade associativa em formato de List

        } else {
            System.out.println("fornecedor não cadastrado");
        }

        //instancie essa classe com shift + f6
    }

}
