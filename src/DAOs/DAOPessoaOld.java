//package DAOs;
//
////@author Radames J Halmeman  - rjhalmeman@gmail.com
//import Conexao.ExecutaSQL;
//import Conexao.UP;
//import Entidades.Pessoa;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DAOPessoaOld {
//
//    ExecutaSQL executaSQL = new ExecutaSQL(UP.getConnection());
//
//    public Pessoa obter(String cpfPessoa) {
//        String sql = "SELECT * FROM pessoa WHERE cpf_pessoa = " + cpfPessoa;
//        //SELECT * FROM pessoaLogin WHERE email_pessoa='rjhalmeman@gmail.com';
//
//        ResultSet rs = executaSQL.executaSelect(sql);
//        Pessoa p = null;
//        try {
//            if (rs.next()) {
//                p = new Pessoa();
//                p.setCpfPessoa(cpfPessoa);
//                p.setNomePessoa(rs.getString("nome_pessoa"));
//               
//                p.setDataNascPessoa(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data_de_nascimento_pessoa")));
//               
//                p.setIdCidade(rs.getInt("cidade_id_cidade"));
//                return p;
//            }
//        } catch (SQLException | ParseException e) {
//            System.out.println("erro " + e.getMessage());
//            return null;
//        }
//
//        return p;
//    }
//
//    public Integer atualizar(Pessoa p) {
//        String sql = "UPDATE pessoa SET "
//                + "nome_pessoa = '" + p.getNomePessoa()+ "', "
//                + "data_de_nascimento_pessoa = '" + new SimpleDateFormat("yyyy-MM-dd").format(p.getDataNascPessoa()) + "', "
//                + "cidade_id_cidade = " + p.getIdCidade()
//                + " WHERE cpf_pessoa = " + p.getCpf_pessoa();
//
//        int result = executaSQL.executaAtualizacaoNoBD(sql);
//        if (result < 0) {
//            System.out.println("Erro na atualização");
//            return null;
//        } else {
//            return 1;
//        }
//    }
//
//    public List<String> listarPessoa() {
//
//        List<String> lp = new ArrayList<>();
//
//        String sql = "SELECT * FROM pessoa;";
//        try {
//            ResultSet rs = executaSQL.executaSelect(sql);
//            while (rs.next()) {
//                Pessoa p = new Pessoa();
//                p.setCpfPessoa(rs.getString("cpf_pessoa"));
//                p.setNomePessoa(rs.getString("nome_pessoa"));
//                p.setDataNascPessoa(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data_de_nascimento_pessoa")));
//                p.setIdCidade(rs.getInt("cidade_id_cidade"));
//                lp.add(p.toString());
//            }
//            return lp;
//        } catch (Exception ex) {
//            System.out.println("erro ao ler o bd " + ex.getMessage());
//        }
//        return null;
//    }
//
//    public Integer excluir(Pessoa p) {
//        String sql = "DELETE FROM pessoa WHERE cpf_pessoa=" + p.getCpf_pessoa();
//        if (executaSQL.executaAtualizacaoNoBD(sql) < 0) {
//            System.out.println("Erro na exclusão");
//            return null;
//        } else {
//            return 1;
//        }
//    }
//
//    public static void main(String[] args) {
////        DAOPessoa daoPessoa = new DAOPessoa();
////        // Pessoa p = daoPessoa.obterPessoaLogin("rjhalmeman@gmail.com");
////        // System.out.println("pessoa "+p);
////        List<String> pessoas = daoPessoa.listarPessoa();
////        if (pessoas != null) {
////            for (String pessoa : pessoas) {
////                System.out.println(pessoa);
////            }
////        }
//    }
//
//}
