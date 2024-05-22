package Main;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import java.util.List;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<String> pessoas = daoPessoa.listarPessoa();
        if (pessoas != null) {
            for (String pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }
        
        System.out.println("\n\n");
        Pessoa p = daoPessoa.obterPessoaLogin("444");
        System.out.println("Pessoa "+p.toString());
        
    }

}
