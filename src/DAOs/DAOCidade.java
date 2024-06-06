package DAOs;

import Conexao.SQLRunner;
import Entidades.Cidade;
import Entidades.Pessoa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DAOCidade extends DAOGenerico<Cidade> {

    public DAOCidade() {
        super();
    }

    public List<String> listarEmOrdemDeNome() {
        DAOCidade daoCidade = new DAOCidade();
        String sql = "SELECT * FROM EnderecoCidadeEstadoPais";

        List<String> lp = daoCidade.executarSQL(sql);
        if (lp != null) {
            return lp;
        } else {
            return null;
        }
    }

//    public List<String> listarQuery() {
//
//        List<String> lp = new ArrayList<>();
//
//       String sql = "SELECT * FROM EnderecoCidadeEstadoPais";
//        try {
//            ResultSet rs = new SQLRunner(c).selectRunner(sql);
//            while (rs.next()) {
//               
//                System.out.println(rs.getString("cpfPessoa"));
//                
//            }
//            System.exit(0);
//            return lp;
//        } catch (Exception ex) {
//            System.out.println("erro ao ler o bd " + ex.getMessage());
//        }
//        return null;
//    }
    public static void main(String[] args) {
        DAOCidade daoCidade = new DAOCidade();
        List<String> listaCidade = daoCidade.listarEmOrdemDeNome();
        for (String cidade : listaCidade) {
            System.out.println(cidade);
        }
    }
}
