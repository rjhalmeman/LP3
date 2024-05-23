package Main;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import java.util.Date;

/**
 *
 * @author radames
 */
public class Testes {

    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        DAOPessoa daoPessoa = new DAOPessoa();
        pessoa.setCpfPessoa("222");
        pessoa.setNomePessoa("Zózo");
        pessoa.setDataNascimentoPessoa(new Date());
        pessoa.setEndereco_idEndereco(1);

        Integer p = daoPessoa.inserir(pessoa);
        if (p != null && p == 1) {
            System.out.println("foi");
        } else {
            System.out.println("erro ao inserir");
        }

    }

}
