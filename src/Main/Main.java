package Main;

import GUIs.PessoaGUI;

/**
 *
 * @author radames
 */
public class Main {

    public static void main(String[] args) {

        PessoaGUI pessoaGUI = new PessoaGUI();

//        DAOPessoa daoPessoa = new DAOPessoa();
//        
//        List<String> pessoas = daoPessoa.listarComoStrings();
//        if (pessoas != null) {
//            for (String pessoa : pessoas) {
//                System.out.println(pessoa);
//            }
//        }
//
//        System.out.println("\n\n Busca uma pessoa específica");
//        Pessoa p = daoPessoa.obter("222","cpfPessoa");
//
//        if (p != null) {
//            System.out.println("Pessoa " + p.toString());
//        }
//        boolean ignorar = true;
//        if (p != null && !ignorar) {
//            // excluir pessoa
//            int e = daoPessoa.excluir("444","cpfPessoa");
//            if (e == 1) {
//                System.out.println("Exclusão realizada");
//            }
//        }
//        
//       // Fetching a Pessoa entity by its CPF
//        String cpf = "111"; // Example CPF
//        Pessoa pessoa = daoPessoa.obter(cpf, "cpf_pessoa");
//
//        if (pessoa != null) {
//            System.out.println("Pessoa found: " + pessoa);
//
//            // Update the Pessoa entity
//            pessoa.setNomePessoa("Updated Name");
//            try {
//                pessoa.setDataNascimentoPessoa(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"));
//            } catch (ParseException e) {
//            }
//            pessoa.setEndereco_idEndereco(2); // Example new city ID
//
//            // Call the atualizar method to update the entity in the database
//            Integer result = daoPessoa.atualizar(pessoa, "cpf_pessoa", cpf);
//
//            if (result != null && result == 1) {
//                System.out.println("Pessoa updated successfully: " + pessoa);
//            } else {
//                System.out.println("Failed to update Pessoa");
//            }
//        } else {
//            System.out.println("Pessoa not found with CPF: " + cpf);
//        }
    }

}
