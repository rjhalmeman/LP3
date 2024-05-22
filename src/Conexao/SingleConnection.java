package Conexao;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.sql.Connection;
import java.sql.DriverManager;


public class SingleConnection {

    private static Connection connection = null;

    private final String serverName = "localhost";    //caminho do servidor do BD  
    private final String myDataBase = "lojinha";        //nome do seu banco de dados 

    private final String url = "jdbc:mysql://" + serverName + "/" + myDataBase;
    private final String userName = "root";        //nome de um usuário de seu BD        
    private final String password = "Lageado001.";    //sua senha de acesso  
    private final String entidade = "";

    static {
        conectar();
    }

    public SingleConnection() {
        conectar();
    }

    private static void conectar() {
        try {
            if (connection == null) {
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);
                // Configurando a nossa conexão com um banco de dados//  
                connection = DriverManager.getConnection("jdbc:mysql://localhost/lojinha", "root", "Lageado001.");
                connection.setAutoCommit(false);
            }
        } catch (Exception e) {
            throw new RuntimeException("erro no iniciar db " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
