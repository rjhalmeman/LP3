package Main;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import java.util.List;

/**
 *
 * @author radames
 */
public class Main {

    public static void main(String[] args) {

        DAOPessoa daoPessoa = new DAOPessoa();
        List<String> pessoas = daoPessoa.listarPessoa();
        if (pessoas != null) {
            for (String pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }

        System.out.println(
                "\n\n Busca uma pessoa específica");
        Pessoa p = daoPessoa.obterPessoaLogin("444");

        System.out.println(
                "Pessoa " + p.toString());

        // excluir pessoa
        int e = daoPessoa.excluir(p);
        if (e == 1) {
            System.out.println("Exclusão realizada");
        }

        pessoas = daoPessoa.listarPessoa();
        if (pessoas != null) {
            for (String pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }

    }

}
