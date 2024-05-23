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
                System.out.println("SQL error...");
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

    public int insertUpdateDeleteRunner(String sql) {
        if (connection != null) {
            java.sql.Statement statement;
            try {
                statement = connection.createStatement();
                int result = statement.executeUpdate(sql);
                connection.commit(); // Commit after the update query
                return result;
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) { // Duplicate entry
                    return 0;
                }
                try {
                    connection.rollback(); // Rollback if there is an error
                } catch (SQLException rollbackEx) {
                    System.out.println("Failed to rollback the transaction.");
                }
                return -1;
            }
        } else {
            System.out.println("Database connection error");
            return -1;
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
