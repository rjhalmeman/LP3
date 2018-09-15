package Main;

import DAOs.DAOFornecedor;
import DAOs.DAOProduto;
import Entidades.Fornecedor;
import Entidades.Produto;
import java.util.List;

/**
 * @author Radames J Halmeman - rjhalmeman@gmail.com
 */
public class RemoverProdutosDeUmFornecedor_Exemplo2 {

    public static void main(String[] args) {
        Fornecedor fornecedor = new Fornecedor();
        DAOFornecedor daoFornecedor = new DAOFornecedor();
        Produto produto = new Produto();
        DAOProduto daoProduto = new DAOProduto();

        //obter um fornecedor
        fornecedor = daoFornecedor.obter(1);
        if (fornecedor != null) {
            List<Produto> lp = fornecedor.getProdutoList();
            lp.remove(daoProduto.obter(3));//remove o produto 3 na lista de produtos daquele fornecedor
            daoFornecedor.atualizar(fornecedor); //atualiza a tabela que possui a entidade associativa em formato de List
            
        } else {
            System.out.println("fornecedor não cadastrado");
        }
        
        //instancie essa classe com shift + f6
        

    }

}
