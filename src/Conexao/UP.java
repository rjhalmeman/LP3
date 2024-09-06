package Conexao;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.sql.Connection;
import java.sql.DriverManager;

public class UP {

    private static Connection connection = null;

    static {
        conectar();
    }

    public UP() {
        conectar();
    }

    private static void conectar() {
        try {
            if (connection == null) {
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);
                // Configurando a nossa conexão com um banco de dados//  
                connection = DriverManager.getConnection("jdbc:mysql://localhost/LojaBasica", "root", "Lageado001.");
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
