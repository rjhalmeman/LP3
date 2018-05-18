package Main;

import entidades.Conta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import tools.Conexao;


/**
 *
 * @author radames
 */
public class ObterSaldoDirect {

    java.text.SimpleDateFormat formatoDataHora = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   /*
    localhost
sistemasensores
radames
lageado
    */
    Conexao c = new Conexao("localhost","controle_contas","radames","lageado","");


    Connection connection = c.getConexao();

    public String obterSaldo(int idConta) {
        DecimalFormat df= new DecimalFormat("#,###,##0.00");
        List<Conta> lista = new ArrayList<>();

        // Sql de inserção  
        String sql = "SELECT * FROM conta WHERE id_conta="+idConta;
        // Cria o statement - Objeto de interação de dados  
        java.sql.Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String s="";
            while (rs.next()) {
                             
               s =   rs.getString("saldo");

                
            }
            s = df.format(Double.valueOf(s));
            return s;

        } catch (Exception e) {

            System.out.println("erro ao ler o BD");
            return null;
        }
    }
    

    public static void main(String[] args) {
        ObterSaldoDirect cd = new ObterSaldoDirect();
        System.out.println("sd "+cd.obterSaldo(1));
    }
}
