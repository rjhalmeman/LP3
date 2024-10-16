/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Testes;

import Conexao.SQLFree;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class SQLTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<String> listaCampos = new ArrayList<>();

        String sql = ("SELECT c.PessoaCpfPessoa, p.nomePessoa \n"
                + "FROM Pessoa p , Cliente c \n"
                + "WHERE  p.cpfPessoa =c.PessoaCpfPessoa;");

        //esta lista deve conter os atributos que vão aparecer na listagem
        listaCampos.add("PessoaCpfPessoa");
        listaCampos.add("nomePessoa");

        List<String> sqlFree = new SQLFree(sql, listaCampos).getResposta();
        for (String string : sqlFree) {
            System.out.println(string);
        }
        
    }

}
