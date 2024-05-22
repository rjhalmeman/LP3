package DAOs;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import Conexao.ExecutaSQL;
import Conexao.SingleConnection;
import Entidades.Pessoa;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOPessoa {

    ExecutaSQL executaSQL = new ExecutaSQL(SingleConnection.getConnection());

    public Pessoa obterPessoaLogin(String cpf_pessoa) {
        String sql = "SELECT * FROM pessoa WHERE cpf_pessoa = " + cpf_pessoa;
        //SELECT * FROM pessoaLogin WHERE email_pessoa='rjhalmeman@gmail.com';

        ResultSet rs = executaSQL.executaSelect(sql);
        Pessoa p = null;
        try {
            if (rs.next()) {
                p = new Pessoa();
                p.setCpf_pessoa(cpf_pessoa);
                p.setNome_pessoa(rs.getString("nome_pessoa"));
                p.setData_de_nascimento_pessoa(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data_de_nascimento_pessoa")));
                p.setCidade_id_cidade(rs.getInt("cidade_id_cidade"));
                return p;
            }
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
            return null;
        }

        return p;
    }

    public List<String> listarPessoa() {

        List<String> lp = new ArrayList<>();

        String sql = "SELECT * FROM pessoa;";
        try {
            ResultSet rs = executaSQL.executaSelect(sql);

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setCpf_pessoa(rs.getString("cpf_pessoa"));
                p.setNome_pessoa(rs.getString("nome_pessoa"));
                p.setData_de_nascimento_pessoa(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data_de_nascimento_pessoa")));
                p.setCidade_id_cidade(rs.getInt("cidade_id_cidade"));
                lp.add(p.toString());
            }
            return lp;
        } catch (Exception ex) {
            System.out.println("erro ao ler o bd "+ ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        DAOPessoa daoPessoa = new DAOPessoa();
        // Pessoa p = daoPessoa.obterPessoaLogin("rjhalmeman@gmail.com");
        // System.out.println("pessoa "+p);
        List<String> pessoas = daoPessoa.listarPessoa();
        if (pessoas != null) {
            for (String pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }
    }

}
