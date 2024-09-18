package DAOs;

import Conexao.SQLRunner;
import Conexao.UP;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class DAOGenerico<T> {

    private final Class<T> type;
    protected SQLRunner selectRunner;
    private boolean showSQL = true; //mostra sql executado no console

    @SuppressWarnings("unchecked")
    public DAOGenerico() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.selectRunner = new SQLRunner(UP.getConnection());
    }

    // Método genérico para execução de UPDATEs
    public int executarUpdate(String sql) {
        System.out.println(showSQL ? sql : "");
        try {
            return selectRunner.insertUpdateDeleteRunner(sql).equals("OK") ? 1 : 0; // Retorna 1 se sucesso
        } catch (Exception e) {
            System.out.println("Erro executando UPDATE: " + e.getMessage());
            return -1; // Indica erro
        }
    }

    // Método genérico para execução de DELETEs
    public int executarDelete(String sql) {
        System.out.println(showSQL ? sql : "");
        try {
            return selectRunner.insertUpdateDeleteRunner(sql).equals("OK") ? 1 : 0; // Retorna 1 se sucesso
        } catch (Exception e) {
            System.out.println("Erro executando DELETE: " + e.getMessage());
            return -1; // Indica erro
        }
    }

    public String inserir(T entity) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + type.getSimpleName() + " (");
        StringBuilder values = new StringBuilder(") VALUES (");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            boolean first = true;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!first) {
                    sql.append(", ");
                    values.append(", ");
                }
                sql.append(field.getName());

                Object value = field.get(entity);
                if (value instanceof String) {
                    values.append("'").append(value.toString().replace("'", "''")).append("'");
                } else if (value instanceof Date) {
                    values.append("'").append(dateFormat.format((Date) value)).append("'");
                } else {
                    values.append("'").append(value).append("'");
                }
                first = false;
            }
            sql.append(values).append(")");
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }

        System.out.println(showSQL ? sql : "");

        String result = selectRunner.insertUpdateDeleteRunner(sql.toString());
        return result.equals("OK") ? "1" : null;
    }

    public T obter(Object id, String idColumnName) {
        String sql = "SELECT * FROM " + type.getSimpleName() + " WHERE " + idColumnName + " = " + "'" + id + "'";

        System.out.println(showSQL ? sql : "");

        ResultSet rs = selectRunner.selectRunner(sql);
        T entity = null;
        try {
            if (rs != null) {
                if (rs.next()) {
                    entity = type.getDeclaredConstructor().newInstance();

                    for (Field field : type.getDeclaredFields()) {
                        field.setAccessible(true);
                        field.set(entity, rs.getObject(field.getName()));
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException | SQLException e) {
            System.out.println("Erro no método obter: " + e.getMessage());
            return null;
        }
        return entity;
    }

    public String atualizar(T entity, String idColumnName, Object idValue) {
        StringBuilder sql = new StringBuilder("UPDATE " + type.getSimpleName() + " SET ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            boolean first = true;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals(idColumnName)) {
                    if (!first) {
                        sql.append(", ");
                    }
                    sql.append(field.getName()).append(" = ");

                    Object value = field.get(entity);
                    if (value instanceof String) {
                        sql.append("'").append(value.toString().replace("'", "''")).append("'");
                    } else if (value instanceof Date) {
                        sql.append("'").append(dateFormat.format((Date) value)).append("'");
                    } else {
                        sql.append("'").append(value).append("'");
                    }
                    first = false;
                }
            }
            sql.append(" WHERE ").append(idColumnName).append(" = '").append(idValue).append("'");
        } catch (IllegalAccessException e) {
            // System.out.println("Erro (atualizar): " + e.getMessage());
            return "Erro (atualizar): " + e.getMessage();
        }
        System.out.println(showSQL ? sql : "");

        String result = selectRunner.insertUpdateDeleteRunner(sql.toString());
        if (!result.equals("OK")) {
            System.out.println(result);
        }
        return result;
    }

    public Integer excluir(Object id, String idColumnName) {
        String sql = "DELETE FROM " + type.getSimpleName() + " WHERE " + idColumnName + " = '" + id + "'";
        System.out.println(showSQL ? sql : "");
        String result = selectRunner.insertUpdateDeleteRunner(sql);
        return result.equals("OK") ? 1 : null;
    }

    public List<T> listar() {
        List<T> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + type.getSimpleName();

        System.out.println(showSQL ? sql : "");

        ResultSet rs = selectRunner.selectRunner(sql);
        try {
            if (rs != null) {
                while (rs.next()) {
                    T entity = type.getDeclaredConstructor().newInstance();
                    for (Field field : type.getDeclaredFields()) {
                        field.setAccessible(true);
                        field.set(entity, rs.getObject(field.getName()));
                    }
                    entities.add(entity);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException | SQLException e) {
            System.out.println("Error no list: " + e.getMessage());
        }
        return entities;
    }

    public List<String> listarComoStrings() {
        List<String> entityStrings = new ArrayList<>();

        String sql = "SELECT * FROM " + type.getSimpleName();

        System.out.println(showSQL ? sql : "");

        ResultSet rs = selectRunner.selectRunner(sql);
        try {
            while (rs.next()) {
                T entity = type.getDeclaredConstructor().newInstance();
                StringBuilder entityString = new StringBuilder();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = rs.getObject(field.getName());
                    if (value != null) {
                        field.set(entity, value);
                        entityString.append(field.getName()).append("=").append(value).append(", ");
                    }
                }
                if (entityString.length() > 0) {
                    entityString.setLength(entityString.length() - 2); // Remove the trailing comma and space
                }
                entityStrings.add(entityString.toString());
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | java.lang.reflect.InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return entityStrings;
    }

    public List<String> executarSQL(String sql) {
        System.out.println("executarSQL");
        System.out.println(showSQL ? sql : "");

        List<String> resultStrings = new ArrayList<>();
        ResultSet rs = selectRunner.selectRunner(sql);
        try {
            while (rs.next()) {
                StringBuilder resultString = new StringBuilder();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = rs.getObject(field.getName());
                    if (value != null) {
                        resultString.append(field.getName()).append("=").append(value).append(", ");
                    }
                }
                if (resultString.length() > 0) {
                    resultString.setLength(resultString.length() - 2); // Remove the trailing comma and space
                }
                resultStrings.add(resultString.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL: " + e.getMessage());
            return null;
        }
        return resultStrings;
    }

}
