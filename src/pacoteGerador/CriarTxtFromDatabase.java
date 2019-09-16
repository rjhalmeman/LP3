/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteGerador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author radames
 */
class LoadFromDatabase {

    List<String> listaMenu = new ArrayList<String>();

    public LoadFromDatabase() {
        menu();
    }

    public List<String> entidadesDoBD() {

        String sql = "SHOW TABLES";
        // Cria o statement - Objeto de interação de dados  
        Statement statement;


        try {
            statement = CriarTxtFromDatabase.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                listaMenu.add(rs.getString(1));
                //  System.out.println(rs.getString(1));

            }
            ResultSetMetaData rsmd = rs.getMetaData();
        } catch (SQLException sqle) {
        }
        return listaMenu;
    }

    public void menu() {
        List<AtributosDaEntidade> listaDeAtributos = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);

        entidadesDoBD();
        for (int i = 0; i < listaMenu.size(); i++) {
            System.out.println(i + 1 + " - " + listaMenu.get(i));
        }
        System.out.println("Qual tabela (nr) => ");
        int resp = teclado.nextInt();

        String sql = "SELECT * FROM " + listaMenu.get(resp - 1);
        // Cria o statement - Objeto de interação de dados  

        try {
            Statement statement = CriarTxtFromDatabase.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            String tableName = rsmd.getTableName(1);
            System.out.println("Tabela=> " + tableName);
            String tipo;
            for (int i = 1; i < numColumns + 1; i++) {
                AtributosDaEntidade a = new AtributosDaEntidade();
                a.setAtributo(rsmd.getColumnName(i));
                tipo = rsmd.getColumnTypeName(i);
                switch (tipo) {
                    case "VARCHAR":
                        a.setTipo("String");
                        break;
                    case "BIGINT":
                        a.setTipo("long");
                        break;
                    case "INT":
                        a.setTipo("int");
                        break;
                    case "DATE":
                        a.setTipo("Date");
                        break;
                    case "DATETIME":
                        a.setTipo("Date");
                        break;
                    case "CHAR":
                        a.setTipo("char");
                        break;
                    case "DOUBLE":
                        a.setTipo("double");
                        break;
                    default:

                }


                a.setLargura(String.valueOf(rsmd.getPrecision(i)));
                listaDeAtributos.add(a);
            }
            ManipulaArquivo arq = new ManipulaArquivo();
            List<String> linhas = new ArrayList<>();
            for (int i = 0; i < listaDeAtributos.size(); i++) {
                linhas.add(listaDeAtributos.get(i).getAtributo() + ";" + listaDeAtributos.get(i).getTipo() + ";" + listaDeAtributos.get(i).getLargura()+ "\n");
            }
            arq.salvarArquivo("src/txts/"+tableName+".txt", linhas);
        } catch (Exception e) {
            System.out.println("erro...");
        }

    }
}

public class CriarTxtFromDatabase {

    public static Conexao c;
    public static Connection connection;

    public static void main(String[] args) {
        c = new Conexao("src/pacoteGerador/local.txt");
        connection = c.getConexao();
        LoadFromDatabase loadFromDatabase = new LoadFromDatabase();



    }
}
