package DAOs;

import Entidades.Pessoa;
import java.util.List;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    public DAOPessoa() {
        super();
    }

    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        List<Pessoa> pessoas = daoPessoa.listar();
        if (pessoas != null) {
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }

        // Fetching a Pessoa entity by its CPF
        String cpf = "222"; // Example CPF
        Pessoa pessoa = daoPessoa.obter(cpf, "cpfPessoa");

        if (pessoa != null) {
            System.out.println("Pessoa found: " + pessoa);
        } else {
            System.out.println("Pessoa not found with CPF: " + cpf);
        }

    }
}
