package Testes;

import Conexao.SQLFree;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class SQLTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        String sql = ("SELECT c.PessoaCpfPessoa, p.nomePessoa , c.rendaCliente \n"
                + "FROM Pessoa p , Cliente c \n"
                + "WHERE  p.cpfPessoa =c.PessoaCpfPessoa ");

        //esta lista deve conter os atributos que vão aparecer na listagem
        List<String> listaCampos = new ArrayList<>();
        listaCampos.add("PessoaCpfPessoa");
        listaCampos.add("nomePessoa");
        listaCampos.add("rendaCliente");

        List<String> sqlFree = new SQLFree(sql, listaCampos).getResposta();
        for (String string : sqlFree) {
            System.out.println(string);
        }

    }

}
