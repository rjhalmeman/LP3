package Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author radames
 */
public class SQLRunner {

    private Connection connection;

    public SQLRunner(Connection c) {
        this.connection = c;
        try {
            this.connection.setAutoCommit(false); // Disable auto-commit
        } catch (SQLException ex) {
            System.out.println("Failed to set auto-commit to false.");
        }
    }

    public ResultSet selectRunner(String sql) {
        if (connection != null) {
            java.sql.Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                connection.commit(); // Commit after the select query
                return rs;
            } catch (SQLException ex) {
                // System.out.println("SQL error...");
                try {
                    connection.rollback(); // Rollback if there is an error
                } catch (SQLException rollbackEx) {
                    System.out.println("Failed to rollback the transaction.");
                }
                return null;
            }
        } else {
            System.out.println("Database connection error");
            return null;
        }
    }

    public String insertUpdateDeleteRunner(String sql) {
        if (connection != null) {
            java.sql.Statement statement;
            try {
                statement = connection.createStatement();
                int result = statement.executeUpdate(sql);

                if (result == 0) {
                    // Nenhuma linha foi afetada, o que significa que a operação não encontrou o item necessário
                    return "Erro: Nenhum registro afetado. O item pode não estar cadastrado.";
                }

                try {
                    connection.commit(); // Commit após a atualização
                    return "OK";
                } catch (SQLException commitEx) {
                    // Captura o erro durante o commit e retorna a mensagem detalhada
                    try {
                        connection.rollback(); // Rollback se houver um erro no commit
                    } catch (SQLException rollbackEx) {
                        return "Erro no commit: " + commitEx.getMessage() + ". Além disso, falhou ao reverter a transação: " + rollbackEx.getMessage();
                    }
                    return "Erro no commit: " + commitEx.getMessage();
                }
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) { // Entrada duplicada
                    return "Erro - PK Duplicada";
                } else if (ex.getErrorCode() == 1452) { // Erro de chave estrangeira
                    return "Erro: Chave estrangeira não encontrada.";
                }
                try {
                    connection.rollback(); // Rollback se houver um erro na execução
                } catch (SQLException rollbackEx) {
                    return "Erro ao executar " + sql + ".\nAlém disso, falhou ao reverter a transação: " + rollbackEx.getMessage();
                }
                return "Erro ao executar " + sql + ": " + ex.getMessage();
            }
        } else {
            return "Database connection error";
        }
    }

    public void commit() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.commit();
            }
        } catch (SQLException ex) {
            System.out.println("Failed to commit the transaction.");
        }
    }

    public void rollback() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            System.out.println("Failed to rollback the transaction.");
        }
    }
}
