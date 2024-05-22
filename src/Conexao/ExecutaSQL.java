package Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author radames
 */
public class ExecutaSQL {

 private Connection connection;

    public ExecutaSQL(Connection c) {
        this.connection = c;        
    }

    public ExecutaSQL(String ArquivoTextoDaConexao) {       
    }
   

    public ResultSet executaSelect(String sql) {
//        System.out.println("sql "+sql);
        if (connection != null) {
            java.sql.Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                return rs;
            } catch (SQLException ex) {
                System.out.println("erro na sql...");
                return null;
            }
        } else {
            System.out.println("Erro de conexão com o Banco de Dados");
            return null;
        }
    }

    public int executaAtualizacaoNoBD(String sql) {
        if (connection != null) {
            java.sql.Statement statement;
            try {
                statement = connection.createStatement();
                return statement.executeUpdate(sql);

            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062)//duplicado {
                {
                    return 0;
                }

                return -1;
            }
        } else {
            System.out.println("Erro de conexão com o Banco de Dados");
            return -1;
        }
    }
}
