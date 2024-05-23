package DAOs;

import Entidades.Pais;
import java.util.List;

public class DAOPais extends DAOGenerico<Pais> {

    public DAOPais() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOPais daoPais = new DAOPais();
        String sql = "SELECT * FROM Pais";
        
        List<String> lp = daoPais.executarSQL(sql);
        if (lp!=null) {
            return lp;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        DAOPais daoPais = new DAOPais();
        List<String> paises = daoPais.listarEmOrdemDeNome();
        for (String pais : paises) {
            System.out.println(pais);
        }

        // Fetching a Pessoa entity by its CPF
//        String cpf = "222"; // Example CPF
//        Pessoa pessoa = daoPessoa.obter(cpf, "cpfPessoa");
//
//        if (pessoa
//                != null) {
//            System.out.println("Pessoa found: " + pessoa);
//        } else {
//            System.out.println("Pessoa not found with CPF: " + cpf);
//        }
    }
}
