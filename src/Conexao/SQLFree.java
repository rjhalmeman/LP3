package Conexao;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author radames
 */
public class SQLFree {

    SQLRunner sqlRunner = new SQLRunner(UP.getConnection());
    private List<String> resposta = new ArrayList<>();

    public SQLFree(String sql, List<String> listaCampos) {   
        resposta = new ArrayList<>();
        ResultSet rs = sqlRunner.selectRunner(sql);

        try {
            if (rs != null) {
                while (rs.next()) {
                    // Obter os dados do ResultSet
                    StringBuilder resultado = new StringBuilder();
                    for (String campo : listaCampos) {
                        // Recuperar o valor correspondente ao campo no ResultSet
                        String valor = rs.getString(campo);
                        resultado.append(valor).append(";"); // Separar com ';'
                    }

                    // Exibir o resultado final
                    resposta.add(resultado.toString().substring(0, resultado.toString().length()-1));
                    //System.out.println(resultado.toString());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro no método obter: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();  // Fechar o ResultSet após o uso
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
    }

    public List<String> getResposta() {
        return resposta;
    }
}
